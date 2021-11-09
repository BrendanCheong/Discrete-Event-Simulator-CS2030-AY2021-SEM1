import java.util.Optional;
import java.util.function.Function;
import java.util.NoSuchElementException;

public class Aggregate<S, T> {

    private final Optional<S> seed;
    private final Optional<T> value;
    private final Optional<Function<S, Pair<S, T>>> func;
    private final boolean validity;

    public Aggregate(Optional<S> seed, Optional<T> value,
        Optional<Function<S, Pair<S, T>>> func, boolean validity) {

        this.seed = seed;
        this.value = value;
        this.func = func;
        this.validity = validity;
    }

    public Aggregate(Optional<S> seed, Optional<T> value, boolean validity) {
        this(seed, value, Optional.empty(), validity);
    }

    public static <S, T> Aggregate<S, T> seed(S seed) {
        return new Aggregate<S, T>(Optional.<S>of(seed), Optional.empty(),
            true);
    }

    public static <S, T> Aggregate<S, T> of(Function<S, Pair<S, T>> funky) {
        return new Aggregate<S, T>(Optional.empty(), Optional.empty(),
            Optional.<Function<S, Pair<S, T>>>of(funky), false);
    }

    public Aggregate<S, T> map(Function<? super S, ? extends S> func, T value) {
        boolean truth = this.seed
            .map((x) -> true)
            .orElseGet(() -> false);
        if (truth) {
            S seed = this.getSeedNotNull();
            S newSeed = func.apply(seed);
            return new Aggregate<>(Optional.<S>of(newSeed), Optional.<T>of(value),
                this.getFunc(), true);
        } else {
            return new Aggregate<>(Optional.empty(), Optional.empty(),
                Optional.empty(), false);
        }
    }

    public Aggregate<S, T> map(Function<? super S, ? extends Pair<S, T>> func) {
        boolean truth = this.seed
            .map((x) -> true)
            .orElseGet(() -> false);
        if (truth) {
            S seed = this.getSeedNotNull();
            Pair<S, T> pair = func.apply(seed);
            S newSeed = pair.first();
            T newValue = pair.second();
            return new Aggregate<>(Optional.<S>of(newSeed), Optional.<T>of(newValue),
                this.getFunc(), true);
        } else {
            return new Aggregate<>(Optional.empty(), Optional.empty(),
                Optional.empty(), false);
        }
    }

    // In this case we must mutate the Aggregate to create a new return type R
    // Since this is not static, and S and T are already defined
    // just define <R> only
    public <R> Aggregate<S, R> flatMap(Function<? super T, ? extends Aggregate<S, R>> func) {
        boolean truth = this.value
            .map((x) -> true)
            .orElseGet(() -> false);
        if (truth) {
            T value = this.getValueNotNull();
            Aggregate<S, R> aggregate = func.apply(value);
            Function<S, Pair<S, R>> newFunc = aggregate.getFuncNotNull();
            S seed = this.getSeedNotNull();
            Pair<S, R> newPair = newFunc.apply(seed);

            return new Aggregate<S, R>(Optional.<S>of(newPair.first()),
                Optional.<R>of(newPair.second()), true);
        } else {
            return new Aggregate<S, R>(Optional.empty(), Optional.empty(),
                Optional.empty(), false);
        }
    }

    // when using Optionals, always have getters for null and not null
    // to avoid type Erasure problems which can force you to type cast
    protected S getSeedNotNull() {
        return this.seed
            .map((x) -> x)
            .orElseThrow();
    }

    protected T getValueNotNull() {
        return this.value
            .map((x) -> x)
            .orElseThrow();
    }

    protected Function<S, Pair<S, T>> getFuncNotNull() {
        return this.func
            .map((x) -> x)
            .orElseThrow();
    }

    private Optional<Function<S, Pair<S, T>>> getFunc() {
        return this.func;
    }

    @Override
    public String toString() {
        if (validity) {
            boolean seed = this.seed
                .map((x) -> true)
                .orElseGet(() -> false);
            boolean value = this.value
                .map((x) -> true)
                .orElseGet(() -> false);

            return seed && value
                ? String.format("(%s, %s)",getSeedNotNull(),
                    getValueNotNull())
                : String.format("(%s)",getSeedNotNull());
        } else {
            try {
                // check if function exists
                Function<S, Pair<S,T>> funky = this.func
                    .map((x) -> x)
                    .orElseThrow();
                return "Aggregate";
            } catch(NoSuchElementException e) {
                return "Invalid Aggregate";
            }
        }
    }

}
