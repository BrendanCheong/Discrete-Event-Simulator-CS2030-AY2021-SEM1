import java.util.Optional;

public class Count<T> extends Aggregate<Integer, T> {

    private static final int counter = 0;

    public Count(T value) {
        super(Optional.<Integer>of(1), Optional.<T>of(value), false);
    }

    private Count(Integer newSeed, T value) {
        super(Optional.<Integer>of(newSeed), Optional.<T>of(value), false);
    }

    public Count<T> map(T value) {
        Integer seed = super.getSeedNotNull();
        return new Count<T>(seed + 1, value);
    }

    // all static constructors need to have their own <T> value beside
    // so I must have <T> Count<T>
    // because I don't know what is the previous value T when static
    public static <T> Count<T> of(T value) {
        return new Count<T>(value);
    }

    public String toString() {
        return String.format("(%s, %s)", super.getSeedNotNull(),
            super.getValueNotNull());
    }

}
