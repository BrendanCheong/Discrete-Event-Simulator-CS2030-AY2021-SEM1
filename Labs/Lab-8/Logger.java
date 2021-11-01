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

    public Logger(Supplier<Pair<T, List<String>>> supper) {
        this.supp = supper;
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
        Supplier<Pair<U,List<String>>> supper = () -> { 
            U value = mapper.apply(this.supp.get().getKey()); 
            List<String> newLog = new ArrayList<>(this.supp.get().getValue());
            newLog.add("" + value);
            return new Pair<U, List<String>>(value, newLog);
        };
        return new Logger<U>(supper);
    }

    public <U> Logger<U> flatMap(Function<? super T, ? extends Logger<? extends U>> flatMapper) {
        Supplier<Pair<U, List<String>>> supper = () -> {
            Logger<? extends U> newLogger = flatMapper.apply(this.supp.get().getKey()); // thats one blow
            // I need to merge the newLogger list with the current list
            // only merge when the size of the current list is more than 1
            List<String> newLoggerList = newLogger.supp.get().getValue();
            int newLoggerEndIndex = newLoggerList.size() - 1;

            @SuppressWarnings("unchecked")
            U newKey = (U) newLoggerList.get(newLoggerEndIndex);
            
            System.out.print("New logger value: " + newKey + "\n");
            List<String> newLog = new ArrayList<>(this.supp.get().getValue());
            System.out.print("Old logger list: " + newLog + "\n");
            IntStream
                .range(0, newLoggerList.size())
                .filter((x) -> !newLog.contains(newLoggerList.get(x)))
                .mapToObj((x) -> newLoggerList.get(x))
                .forEach((x) -> newLog.add("" + x));
            System.out.print("New Created Logger List: " + newLog + "\n");
            return new Pair<U, List<String>>(newKey, newLog);
        };
        return new Logger<U>(supper);
    }

    public <U> Logger<U> testSubject(Logger<U> trueLogger, Logger<U> falseLogger) {
        if (true) {
            return trueLogger;
        }
        return falseLogger;
    }

    public String getHistory() {
        List<String> currentHistory = this.supp.get().getValue();
        int lastIndex = currentHistory.size() - 1;

        String currentLog = String.format("Logger[%s]", 
            currentHistory.get(lastIndex));

        String answer = IntStream
            .range(1, currentHistory.size())
            .mapToObj((x) -> new Pair<String, String>(currentHistory.get(x - 1),
                currentHistory.get(x)).toString())
            .reduce("", (x, y) -> x + y);
        
        return currentLog + answer;
    }


    @Override
    public String toString() {
        Supplier<String> historyLog = () -> getHistory();
        return historyLog.get();
    }

}
