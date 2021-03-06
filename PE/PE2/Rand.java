import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.Random;
import java.util.stream.Stream;

public class Rand<T> {

    private final int seed;
    private final Optional<Random> nextVal;
    private final Optional<Function<Integer, T>> mapper;

    private Rand(int seed, Optional<Random> nextVal, 
            Optional<Function<Integer, T>> mapper) {
        this.seed = seed;
        this.nextVal = nextVal;
        this.mapper = mapper;
    }

    public static Rand<Integer> of(int seed) {
        return new Rand<>(seed, Optional.empty(), Optional.of(x -> x));
    }

    public Rand<T> next() {
        Random newRand = new Random(this.seed);
        int newSeed = newRand.nextInt(Integer.MAX_VALUE);
        Optional<Random> newVal = Optional.<Random>of(new Random(newSeed));
        return new Rand<>(newSeed, newVal, this.mapper);
    }

    public Stream<T> stream() {
        UnaryOperator<Integer> operator = (integer) -> {
            int seed = integer;
            int nextVal = Rand.of(seed).next().get();
            return (Integer) nextVal;
        };
        return Stream.iterate(this.seed, operator).map(getMapper());
    }

    public static <R> Stream<R> randRange(int seed, Function<Integer, ? extends R> func) {
        Rand<Integer> newRandObj = Rand.of(seed);
        Stream<Integer> newStream = newRandObj.stream();
        return newStream.map(func);
    }

    public <R> Rand<R> map(Function<T, R> mapper) {
        return new Rand<>(this.seed, this.nextVal, 
            Optional.of(mapper.compose(getMapper())));
    }

    public <R> Rand<R> flatMap(Function<? super T, ? extends Rand<R>> flatMapper) {;
        Function<Rand<R>, R> unwrapper = x -> x.get();
        return map(unwrapper.compose(flatMapper));
    }

    public Function<Integer, T> getMapper() {
        return mapper.map(x -> x).orElseThrow();
    }

    public T get() {
        return getMapper().apply(this.seed);
    }

    @Override
    public String toString() {
        return "Rand";
    }
}
