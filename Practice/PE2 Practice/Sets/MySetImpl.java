import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class MySetImpl<E> implements MySet<E> {

    private final List<E> myItems;

    public MySetImpl() {
        this.myItems = new ArrayList<>();
    }

    public MySetImpl(List<E> items) {
        this.myItems = items;
    }

    public List<E> getItems() {
        return this.myItems;
    }

    @SafeVarargs
    public static <E> MySet<E> of (E...e) throws DuplicateNotAllowedException {
        long noDuplicateSize = Arrays
            .stream(e)
            .distinct()
            .count();
        long test = Optional.<Long>of(noDuplicateSize)
            .filter((x) -> x == e.length)
            .orElseThrow(() -> {
                return new DuplicateNotAllowedException("Sets cannot have duplicates!");
            }
        );
        return new MySetImpl<E>(new ArrayList<E>(Arrays.asList(e)));
    }

    public boolean isEmpty() {
        List<E> items = this.myItems;
        return items.size() == 0;
    }

    public int size() {
        List<E> items = this.myItems;
        return items.size();
    }

    public boolean remove(Object o) {
        List<E> items = this.myItems;
        return items.remove(o);
    }

    public boolean contains(Object o) {
        List<E> items = this.myItems;
        return items.contains(o);
    }

    public boolean add(E e) {
        List<E> items = this.myItems;
        return items.add(e);
    }

    public void clear() {
        List<E> items = this.myItems;
        items.clear();
    }

    public String join(String delimiter) {
        List<E> items = this.myItems;
        return items
            .stream()
            .map((x) -> x + delimiter)
            .limit(items.size() - 1)
            .reduce("", (x, y) -> x + y) + items.get(items.size() - 1);
    }

    public Optional<E> get(Predicate<? super E> pred) {
        List<E> items = this.myItems;
        Optional<E> answer = items
            .stream()
            .filter((x) -> pred.test(x))
            .findFirst();
        return answer;
    }

    public List<E> duplicate(int copies) {
        List<E> items = this.myItems;
        return items
            .stream()
            .map((x) -> x)
            .collect(Collectors.toList());
    }

    public MySet<E> filter(Predicate<? super E> pred) {
        List<E> items = this.myItems;
        List<E> newList = items
            .stream()
            .map((x) -> x)
            .filter((x) -> pred.test(x))
            .collect(Collectors.toCollection(() -> new ArrayList<E>()));
        return new MySetImpl<E>(newList);
    }

    public E reduce(E seed, BinaryOperator<E> accumulator) {
        List<E> items = this.myItems;
        E result = items
            .stream()
            .reduce(seed, (x, y) -> accumulator.apply(x, y));
        return result;
    }

    // I must have declared type R as <R> MySet<R>
    public <R> MySet<R> map(Function<? super E, ? extends R> mapper) {
        List<E> items = this.myItems;
        List<R> newItems = items
            .stream()
            .map((x) -> mapper.apply(x))
            .collect(Collectors.toCollection(() -> new ArrayList<R>()));
        return new MySetImpl<R>(newItems);
    }

    public List<E> sort(Comparator<? super E> comp) {
        List<E> items = this.myItems;
        return items
            .stream()
            .sorted(comp)
            .collect(Collectors.toList());
    }

    //! How to merge two lists using concat
    //! concat allows me to merge list together
    //! distinct is like sets, removes duplicates
    public MySet<E> union(MySet<E> otherSet) {
        List<E> items = this.myItems;
        List<E> otherSetItems = otherSet.getItems();
        List<E> newList = Stream
            .concat(items.stream(), otherSetItems.stream())
            .sorted()
            .distinct()
            .collect(Collectors.toList());
        return new MySetImpl<E>(newList);
    }

    public MySet<E> intersect(MySet<E> otherSet) {
        List<E> items = this.myItems;
        List<E> otherSetItems = otherSet.getItems();
        List<E> newList = items
            .stream()
            .filter((x) -> otherSetItems.contains(x))
            .sorted()
            .distinct()
            .collect(Collectors.toList());
        return new MySetImpl<E>(newList);
    }

    public String toString() {
        List<E> items = this.myItems;
        String allListedItems = items
            .stream()
            .map((x) -> String.format(" %s,", x.toString()))
            .reduce("", (x, y) -> x + y);
        String answer = String.format("MySet contains:%s", allListedItems);
        return items.size() > 0
            ? answer.substring(0, answer.length() - 1)
            : String.format("MySet contains: NOTHING!");
    }
}
