import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.Optional;
import java.util.ArrayList;

public class InfiniteListImpl<T> implements InfiniteList<T> {
    
    private final Lazy<T> head;
    private final Lazy<InfiniteListImpl<T>> tail;
    
    public InfiniteListImpl() {
        this.head = Lazy.<T>ofNullable(null);
        this.tail = Lazy.of(() -> new EmptyList<T>());
    }
    private InfiniteListImpl(Lazy<T> head, Lazy<InfiniteListImpl<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supper) {
        Lazy<T> newHead = Lazy.of(supper);
        Lazy<InfiniteListImpl<T>> newTail = Lazy.of(() -> {
            // I would use this. but since its static use InfiniteListImpl.
            return InfiniteListImpl.generate(supper);
        });
        return new InfiniteListImpl<>(newHead, newTail);
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> iterator) {
        Lazy<T> newHead = Lazy.ofNullable(seed);
        Lazy<InfiniteListImpl<T>> newTail = Lazy.of(() -> {
            // recursively iterate 
            return InfiniteListImpl.iterate(iterator.apply(seed), iterator);
        });
        return new InfiniteListImpl<>(newHead, newTail);
    }

    public InfiniteList<T> peek() {
        this.head
            .get()
            .ifPresent((x) -> System.out.println(x));
        return this.tail
            .get()
            .orElseThrow();
    }

    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = this.head
            .map(mapper);
        Lazy<InfiniteListImpl<R>> newTail = Lazy.of(() -> this.tail
            .get()
            .orElseThrow()
            .map(mapper));
        return new InfiniteListImpl<R>(newHead, newTail);
    }

    public InfiniteListImpl<T> filter(Predicate<? super T> pred) {
        Lazy<T> newHead = this.head
            .filter(pred);
        Lazy<InfiniteListImpl<T>> newTail = Lazy.of(() -> this.tail
            .get()
            .orElseThrow()
            .filter(pred));
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public boolean isEmpty() {
        return false;
    }

    public InfiniteListImpl<T> takeWhile(Predicate<? super T> pred) {
        Lazy<T> filtered = this.head
            .filter(pred);
        return new InfiniteListImpl<>(filtered, Lazy.of(() -> this.head
            .get()
            .isPresent() && filtered.get().isEmpty()
                    ? new EmptyList<T>()
                    : this.tail.get().orElseThrow().takeWhile(pred)));
    }

    public InfiniteListImpl<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<T>();
        } else if (n == 1) {
            return new InfiniteListImpl<T>(head, Lazy.of(() -> head.get().isPresent()
                        ? new EmptyList<>()
                        : this.tail.get().orElseThrow().limit(n)));
        } else {
            return new InfiniteListImpl<T>(head, this.tail.map(x -> head.get().isPresent()
                        ? x.limit(n - 1)
                        : x.limit(n)));
        }
    }

    public void forEach(Consumer<? super T> action) {
        InfiniteListImpl<T> curr = this;
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
        Optional<T> result = head.get();
        InfiniteListImpl<T> curr = this.tail.get().orElseThrow();
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
        U result = identity;
        InfiniteListImpl<T> curr = this;
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
