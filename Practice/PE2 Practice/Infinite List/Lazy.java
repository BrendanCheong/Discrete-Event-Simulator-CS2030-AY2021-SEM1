import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Lazy<T> {

    private Optional<T> cache;
    private Supplier<? extends T> supplier;
    private boolean evaluated;

    private Lazy(T v) {
        this.cache = Optional.<T>ofNullable(v);
        this.supplier = () -> v;
        evaluated = true;   
    }

    private Lazy(Supplier<? extends T> supplier) {
        this.cache = Optional.<T>empty();
        this.supplier = supplier;
        evaluated = false;
    }

    public static <T> Lazy<T> ofNullable(T cache) {
        return new Lazy<>(cache);
    }

    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    public <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        return new Lazy<>(() -> get().map(mapper).orElseThrow());
    }

    public Lazy<T> filter(Predicate<? super T> pred) {
        return new Lazy<>(() -> get().filter(pred).orElseThrow());   
    }

    public Optional<T> get() {
        try {
            if (!evaluated) {
                evaluated = true;
                this.cache = Optional.<T>ofNullable(this.supplier.get());
                this.supplier = () -> this.cache
                    .map((x) -> x)
                    .orElseThrow();
            } return this.cache;
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public boolean isPresent(Optional<T> opt) {
        return opt
            .map((x) -> true)
            .orElseGet(() -> false);
    }

    @Override
    public String toString() {
        if (evaluated) {
            // check if cache is empty
            boolean truth = this.isPresent(this.cache);
            return truth
                ? String.format("Lazy[%s]", this.cache
                    .map((x) -> x)
                    .orElseThrow())
                : "Lazy[null]";
        } else {
            return "Lazy[?]";
        }
    }
}
