import java.util.Optional;
import java.util.List;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Function;

public interface MySet<E> {

    public boolean isEmpty();

    public boolean contains(Object o);

    public boolean remove(Object o);

    public boolean add(E e);

    public void clear();

    public int size();

    public String join(String delimiter);

    public Optional<E> get(Predicate<? super E> pred);

    public List<E> duplicate(int copies);

    public MySet<E> filter(Predicate<? super E> pred);

    public E reduce(E seed, BinaryOperator<E> accumulator);

    public <R> MySet<R> map(Function<? super E, ? extends R> mapper);

    public List<E> sort(Comparator<? super E> comp);

    public MySet<E> union(MySet<E> otherSet);

    public MySet<E> intersect(MySet<E> otherSet);

    public List<E> getItems();

}
