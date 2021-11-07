import java.util.Optional;
import java.util.List;

public interface MySet<E> {

    public boolean isEmpty();

    public boolean contains(Object o);

    public boolean remove(Object o);

    public boolean add(E e);

    public void clear();

    public int size();
//
//    public String join();
//
//    public Optional<E> get();
//
//    public List<E> duplicate();

}
