import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class Logger<T> {

    private final Supplier<Pair<T, List<String>>> supp;

    public Logger(T input, List<String> previousValue) {
        Pair<T, List<String>> pair = new Pair<>(input, previousValue);
        Supplier<Pair<T, List<String>>> supp = () -> pair;
        this.supp = supp;
    }

    public static <T> Logger<T> of(T input) {
        Optional<T> value = Optional.<T>ofNullable(input);
        try {
            T actualValue = value
                .map((x) -> x)
                .orElseThrow();
            if (input instanceof Logger) {
                throw new IllegalArgumentException("already a logger");
            } else {
                List<String> previousValue = new ArrayList<>();
                previousValue.add("" + input);
                return new Logger<T>(input, previousValue);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("argument cannot be null");
        }
    }

    public <U> Logger<U> map(Function<? super T, ? extends U> mapper) {
        Supplier<Logger<U>> supper = () -> { // Supplier <Pair<T, List<String>>> supp
            U value = mapper.apply(this.supp.get().getKey()); // get Key is to get K from Pair<K,V>
            List<String> newLog = new ArrayList<>(this.supp.get().getValue()); // get Value is for V in Pair<K,V>
            newLog.add("" + value);
            return new Logger<U>(value, newLog);
        };
        return supper.get();
    }

    public <U> Logger<U> testSubject(Logger<U> trueLogger, Logger<U> falseLogger) {
        if (true) {
            return trueLogger;
        }
        return falseLogger;
    }

    public String getHistory() {
        List<String> currentHistory = this.supp.get().getValue();
        String answer = IntStream
            .range(1, currentHistory.size())
            .mapToObj((x) -> new Pair<String, String>(currentHistory.get(x - 1),
                currentHistory.get(x)).toString())
            .reduce("", (x, y) -> x + y);
        return answer;
    }


    @Override
    public String toString() {
        int size = this.supp.get().getValue().size();
        String currentLog = String.format("Logger[%s]", this.supp.get().getKey());
        return  size > 1 ? currentLog + getHistory() : currentLog;
    }

}
