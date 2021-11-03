import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

public class Logger<T> {

    private final Supplier<Pair<T, List<String>>> supp;

    /**
     * Creates a Logger, takes in initial value and history of previous values.
     * @param input the initial value
     * @param previousValue a list of previous values
     */
    public Logger(T input, List<String> previousValue) {
        Pair<T, List<String>> pair = new Pair<>(input, previousValue);
        Supplier<Pair<T, List<String>>> supp = () -> pair;
        this.supp = supp;
    }

    public Logger(Supplier<Pair<T, List<String>>> supper) {
        this.supp = supper;
    }

    public Supplier<Pair<T, List<String>>> getSupp() {
        return this.supp;
    }

    /**
     * Creates a completely new Logger with no history.
     * @param <T> Creates a Logger of chosen type
     * @param input the initial value, only used to start a completely new Logger
     * @return a completely new Logger with no history
     */
    public static <T> Logger<T> of(T input) {
        Optional<T> value = Optional.<T>ofNullable(input);
        try {
            T actualValue = value
                .map((x) -> x)
                .orElseThrow();
            if (input instanceof Logger) {
                throw new IllegalArgumentException("already a Logger");
            } else {
                List<String> previousValue = new ArrayList<>();
                previousValue.add("Starting Value");
                return new Logger<T>(input, previousValue);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("argument cannot be null");
        }
    }

    /**
     * Maps the function onto the current Logger to mutate and change it.
     * <p> Will avoid adding duplicate pairs over and over again </p>
     * <p> Will avoid adding "Starting Value" String again </p>
     * @param <U> Mutate the initial value type to chosen type U
     * @param mapper the function to mutate the initial value
     * @return a new Logger with new initial value and updated history
     */
    public <U> Logger<U> map(Function<? super T, ? extends U> mapper) {
        Supplier<Pair<U,List<String>>> supper = () -> {

            Pair<T, List<String>> currentPair = this.supp.get();
            T oldValue = currentPair.getKey();
            U newValue = mapper.apply(currentPair.getKey()); 
            List<String> newLog = new ArrayList<>(currentPair.getValue());

            if (!newLog.get(newLog.size() - 1).equals("" + oldValue)) {
                newLog.add("" + oldValue);
            }
            newLog.add("" + newValue);

            return new Pair<U, List<String>>(newValue, newLog);
        };
        return new Logger<U>(supper);
    }

    /**
     * FlatMaps the function onto the current Logger to mutate and change it.
     * <p> Will merge the two histories together, from new Logger onto old Logger </p>
     * <p> Will prevent duplicate pairs and avoid adding "Starting Value" again </p> 
     * @param <U> Mutate the initial value type to chosen type U
     * @param flatMapper the function to mutate the initial value to create a Logger
     * @return a new Logger with new initial value and updated history
     */
    public <U> Logger<U> flatMap(Function<? super T, ? extends Logger<? extends U>> flatMapper) {
        Supplier<Pair<U, List<String>>> supper = () -> {
            Pair<T, List<String>> currentPair = this.supp.get();
            List<String> currentList = new ArrayList<>(currentPair.getValue());
            
            Logger<? extends U> newLogger = flatMapper.apply(currentPair.getKey());
            Pair<? extends U, List<String>> newLoggerPair = newLogger.supp.get();
            U newKey = newLoggerPair.getKey();
            List<String> newLoggerList = newLoggerPair.getValue();

            // I need to merge the newLoggerList with the current list
            List<String> newHistory = this.merge(currentList, newLoggerList);
            return new Pair<U, List<String>>(newKey, newHistory);
        };
        return new Logger<U>(supper);
    }

    /**
     * Checks if input Logger is equal to current Logger.
     * <p> Non-Loggers will automatically return false </p>
     * @param logger the Logger inputed, Non-Loggers are allowed
     * @return whether the two Loggers are equal
     */
    public boolean equals(Logger<T> logger) {
        Pair<T, List<String>> inputPair = logger.supp.get();
        Pair<T, List<String>> currentPair = this.supp.get();
        return inputPair.equals(currentPair);
    }

    /**
     * Decides which logger to output based on the predicate's true or false.
     * @param pred the predicate to evaluate against the current log
     * @param trueLogger output this if predicate is true;
     * @param falseLogger output this if predicate is false;
     * @return output the correct Logger according to predicate, is lazily evaluated
     */
    public Logger<T> test(Predicate<? super T> pred, Logger<T> trueLogger, Logger<T> falseLogger) {
        boolean result = pred.test(this.supp.get().getKey());

        return result
            ? trueLogger
            : falseLogger;
    }

    /**
     * Merges two lists without any duplicates using Sets.
     * @param target the list to be merged into
     * @param input the list we use to merge into the target list
     * @return the new merged list.
     */
    public List<String> merge(List<String> target, List<String> input) {
        Set<String> mergedSet = new LinkedHashSet<>(target);
        mergedSet.addAll(input);
        List<String> mergedList = new ArrayList<>(mergedSet);
        return mergedList;
    }

    /**
     * Turns the Logger's history into a readable String.
     * <p> Will only print history if the Logger was mapped </p>
     * <p> If not mapped before, output only the initial value </p>
     * @return prints each value in the Logger's history in pairs
     */
    public String getHistory() {
        Pair<T, List<String>> pair = this.supp.get();
        List<String> currentHistory = pair.getValue();

        String currentLog = String.format("Logger[%s]", 
            pair.getKey());
        
        if (currentHistory.size() == 1) {
            return currentLog;
        }

        String answer = IntStream
            .range(2, currentHistory.size())
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
