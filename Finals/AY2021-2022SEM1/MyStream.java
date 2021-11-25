import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;
abstract class MyStream<T> {
    static <T> MyStream<T> generate(Supplier<T> seed) {
        return new MyStream<T>() {
            public T get() {
                return seed.get();
            }
        };
    }
    abstract T get();

    public void forEach(Consumer<? super T> action, int count) {
        // action.accept(this.get());
        for (int i = 0; i < count ; ++i) {
            action.accept(this.get());
        }
    }

    public <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        // R newHead = mapper.apply(this.get());
        Supplier<R> seed = () -> mapper.apply(this.get());
        return MyStream.generate(seed);
    }
}
