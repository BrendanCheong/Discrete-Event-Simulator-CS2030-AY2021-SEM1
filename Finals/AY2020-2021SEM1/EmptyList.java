import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;

public class EmptyList<T> extends LazyList<T> {

    public EmptyList() {
        super();
    }

    @Override
    public <R> EmptyList<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<R>();
    }

    @Override
    public EmptyList<T> filter(Predicate<? super T> pred) {
        return this;
    }

    @Override
    public EmptyList<T> peek() {
        return this;
    }

    @Override
    public EmptyList<T> takeWhile(Predicate<? super T> pred) {
        return this;
    }

    public EmptyList<T> limit(long n) {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}