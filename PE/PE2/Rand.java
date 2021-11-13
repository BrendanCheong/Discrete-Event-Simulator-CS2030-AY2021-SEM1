import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.Random;
import java.util.stream.Stream;

public class Rand<T> {

    private final int seed;
    private final boolean started;
    private final Optional<Random> nextVal;
    private final Optional<Function<T, Rand<T>>> mapper;

    private Rand(int seed, boolean started, Optional<Random> nextVal, 
            Optional<Function<T, Rand<T>>> mapper) {
        this.seed = seed;
        this.started = started;
        this.nextVal = nextVal;
        this.mapper = mapper;
    }

    public static <T> Rand<T> of(int seed) {
        return new Rand<>(seed, true, Optional.empty(), Optional.empty());
    }

    public Rand<T> next() {

        if (started) {
            Random newRand = new Random(this.seed);
            int newSeed = newRand.nextInt(Integer.MAX_VALUE);
            Optional<Random> newVal = Optional.of(new Random(newSeed));
            return new Rand<>(newSeed, false, newVal, this.mapper);
        } else {
            Random newRand = new Random(this.seed);
            int newSeed = newRand.nextInt(Integer.MAX_VALUE);
            Optional<Random> newVal = Optional.of(new Random(newSeed));
            return new Rand<>(newSeed, false, newVal, this.mapper);
        }
    }

    public Stream<Integer> stream() {
        UnaryOperator<Integer> operator = (integer) -> {
            int seed = integer;
            int nextVal = Rand.of(seed).next().get();
            return (Integer) nextVal;
        };

        return Stream.iterate(this.seed, operator);
    }

    public static <R> Stream<R> randRange(int seed, Function<Integer, ? extends R> func) {
        Rand<Integer> newRandObj = Rand.of(seed);
        Stream<Integer> newStream = newRandObj.stream();
        return newStream.map(func);
    } 

    public <R> Rand<T> map(Function<T, Rand<T>> mapper) {
        return new Rand<>(this.seed, this.started, this.nextVal, Optional.of(mapper));
    }

    public <R> int flatMap(Function<? super T, ? extends Rand<R>> flatMapper) {
        return 1;
    }

    public int get() {
        // check if function exists
        boolean truth = this.mapper
            .map((x) -> true)
            .orElseGet(() -> false);
        return this.seed;
    }

    @Override
    public String toString() {
        return "Rand";
    }
}
