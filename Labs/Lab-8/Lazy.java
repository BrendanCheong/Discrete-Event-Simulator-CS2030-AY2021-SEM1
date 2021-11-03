import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Optional;

public class Lazy<T> {

    private Optional<T> v;
    private Supplier<? extends T> s;
    private boolean evaluated;

    private Lazy(T v) {
        this.v = Optional.<T>ofNullable(v);
        s = () -> v;
        evaluated = true;   
    }

    private Lazy(Supplier<? extends T> s) {
        this.v = Optional.empty();
        this.s = s;
        evaluated = false;
    }

    public static <T> Lazy<T> ofNullable(T v) {
        return new Lazy<>(v);
    }

    public static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        return new Lazy<>(() -> supplier.get());
    }

    public <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        return new Lazy<>(() -> get().map(mapper).orElseThrow());
    }

    public Lazy<T> filter(Predicate<? super T> pred) {
        return new Lazy<>(() -> get().filter(pred).orElseThrow());   
    }

    public Optional<T> get() {
        if (!evaluated) {
            evaluated = true;
            v = Optional.<T>of(s.get());
            s = () -> v.get();
        }
        return v;  
    }

    @Override
    public String toString() {
        if (evaluated) {
            if (v.isEmpty()) {
                return "null";
            } else {
                return v.toString();
            }
        } else {
            return "?";
        }
    }
}