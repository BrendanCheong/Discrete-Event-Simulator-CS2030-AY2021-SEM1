import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.Optional;
import java.util.ArrayList;

public class LazyList<T> implements InfiniteList<T> {
    
    private final Lazy<T> head;
    private final Lazy<LazyList<T>> tail;
    
    public LazyList() {
        this.head = Lazy.<T>ofNullable(null);
        this.tail = Lazy.of(() -> new EmptyList<T>());
    }
    private LazyList(Lazy<T> head, Lazy<LazyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    public static <T> LazyList<T> generate(Supplier<? extends T> supper) {
        Lazy<T> newHead = Lazy.of(supper);
        Lazy<LazyList<T>> newTail = Lazy.of(() -> {
            // I would use this. but since its static use LazyList.
            return LazyList.generate(supper);
        });
        return new LazyList<>(newHead, newTail);
    }

    public static <T> LazyList<T> iterate(T seed, UnaryOperator<T> iterator) {
        Lazy<T> newHead = Lazy.ofNullable(seed);
        Lazy<LazyList<T>> newTail = Lazy.of(() -> {
            // recursively iterate 
            return LazyList.iterate(iterator.apply(seed), iterator);
        });
        return new LazyList<>(newHead, newTail);
    }

    public InfiniteList<T> peek() {
        this.head
            .get()
            .ifPresent((x) -> System.out.println(x));
        return this.tail
            .get()
            .orElseThrow();
    }

    public <R> LazyList<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = this.head
            .map(mapper);
        Lazy<LazyList<R>> newTail = Lazy.of(() -> this.tail
            .get()
            .orElseThrow()
            .map(mapper));
        return new LazyList<R>(newHead, newTail);
    }

    public LazyList<T> filter(Predicate<? super T> pred) {
        Lazy<T> newHead = this.head
            .filter(pred);
        Lazy<LazyList<T>> newTail = Lazy.of(() -> this.tail
            .get()
            .orElseThrow()
            .filter(pred));
        return new LazyList<T>(newHead, newTail);
    }

    public boolean isEmpty() {
        return false;
    }

    public LazyList<T> takeWhile(Predicate<? super T> pred) {
        Lazy<T> filtered = this.head
            .filter(pred);
        return new LazyList<>(filtered, Lazy.of(() -> this.head
            .get()
            .isPresent() && filtered.get().isEmpty()
                    ? new EmptyList<T>()
                    : this.tail.get().orElseThrow().takeWhile(pred)));
    }

    public LazyList<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<T>();
        } else if (n == 1) {
            return new LazyList<T>(head, Lazy.of(() -> head.get().isPresent()
                        ? new EmptyList<>()
                        : this.tail.get().orElseThrow().limit(n)));
        } else {
            return new LazyList<T>(head, this.tail.map(x -> head.get().isPresent()
                        ? x.limit(n - 1)
                        : x.limit(n)));
        }
    }

    public void forEach(Consumer<? super T> action) {
        // Consumer(T) => T -> void
        LazyList<T> curr = this;
        while(!curr.isEmpty()) {
            curr.head.get().ifPresent(action);
            curr = curr.tail.get().orElseThrow();
        }
    }
    public Object[] toArray() {
        ArrayList<Object> list = new ArrayList<>();
        forEach(x -> list.add(x));
        return list.toArray();
    }

    public long count() {
        return this.map(x -> 1L).reduce(0L, (x, y) -> x + y);
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        // BinaryOperator apply(T, T) => (T, T) -> T
        Optional<T> result = head.get();
        LazyList<T> curr = this.tail.get().orElseThrow();
        while (!curr.isEmpty()) {
            Optional<T> head = curr.head.get();
            if (result.isEmpty()) {
                result = head;
            } else if (head.isPresent()) {
                result = Optional.ofNullable(accumulator.apply(result.orElseThrow(), head.orElseThrow()));
            }
            curr = curr.tail.get().orElseThrow();
        }
        return result;
    }

    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        // BiFunction apply(T, U, V) => (T, U) -> V
        U result = identity;
        LazyList<T> curr = this;
        while(!curr.isEmpty()) {
            Optional<T> head = curr.head.get();
            if (head.isPresent()) {
                result = accumulator.apply(result, head.orElseThrow());
            }
            curr = curr.tail.get().orElseThrow();
        }
        return result;
    }
}    
