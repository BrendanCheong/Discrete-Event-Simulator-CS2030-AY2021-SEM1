import java.util.stream.IntStream;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class MySetImpl<E> implements MySet<E> {

    private final List<E> myItems;

    public MySetImpl() {
        this.myItems = new ArrayList<>();
    }

    public MySetImpl(List<E> items) {
        this.myItems = items;
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
