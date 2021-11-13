import java.util.Random;
import java.util.stream.Stream;
import java.util.function.Function;

class Rand<T> {
    
    private final int seed;
    private final int depth;
    private final Function<Integer, T> mapper;

    Rand(int seed, int depth, Function<Integer, T> mapper) {
        this.seed = seed;
        this.depth = depth;
        this.mapper = mapper;
    }

    public static Rand<Integer> of(int seed) {
        return new Rand<Integer>(seed, 0, x -> x);
    }

    public T get() {
        int res = seed;
        return mapper.apply(res);
    }

    public Stream<T> stream() {
        return Stream.<Rand<T>>iterate(this, x -> x.next()).map(x -> (T) x.get());
    }

    public static <U> Stream<U> randRange(int seed, Function<Integer, U> func) {
        return Rand.of(seed).stream().map(x -> func.apply((int) x));
    }

    public Rand<T> next() {
        return new Rand<T>(new Random(seed).nextInt(Integer.MAX_VALUE), depth + 1, mapper);
    }

    public Rand<T> merge(int depth) {
        return new Rand<T>(seed, this.depth + depth, mapper);
    }

    public <U> Rand<U> flatMap(Function<T, Rand<U>> func) {
        Rand<U> tempRand = new Rand<U>(seed, depth, x -> func.apply(mapper.apply(x)).get());
        return tempRand.merge(depth);
    }
    
    public <U> Rand<U> map(Function<T, U> func) {
        return new Rand<U>(seed, depth, x -> func.apply(mapper.apply(x)));
    }

    @Override
    public String toString() {
        return "Rand";
    }
}
