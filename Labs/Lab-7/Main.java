import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Main {

    private static boolean isPrime(int n) {
        return IntStream
            .iterate(2, (x) -> x * x <= n, (x) -> ++x)
            .noneMatch((i) -> n % i == 0);
    }

    /**
     * Counts the number of Pairs of Twin Primes.
     * @param n given a number, counts the number of twin prime pairs to that number
     * @return the number of twin prime pairs starting from 2
     */
    static long countTwinPrimes(int n) {
        return IntStream
            .range(3, n + 1)
            .filter((x) -> isPrime(x) && (isPrime(x - 2) || isPrime(x + 2)))
            .map((x) -> 1)
            .sum();
    }

    /**
     * Reverses a given string.
     * @param str reverses the given string by adding backwards
     * @return the reversed String inputed
     */
    static String reverse(String str) {
        return str
            .chars() // Instream map only allows int -> int, so I use mapToObj
            .mapToObj((ascii) -> String.valueOf((char) ascii))
            .reduce("", (x, y) -> y + x); // you cannot chain 2 mapToObj together
    }

    /**
     * Counts the number of repeated Integer pairs in an array.
     * @param array uses Lists to count the number of repeated pairs from input array
     * @return the number of pairs found
     */
    static long countRepeats(int...array) {
        List<Integer> intList = new ArrayList<>();
        intList.add(420);
        IntStream intStream = Arrays.stream(array);
        intStream
            .forEachOrdered((x) -> intList.add(x));
        int arrayLength = array.length;

        List<Integer> countedList = IntStream
            .range(1, arrayLength)
            .filter((x) -> intList.get(x) == intList.get(x + 1) &&
                intList.get(x) != intList.get(x - 1))
            .mapToObj((x) -> intList.get(x))
            .collect(Collectors.toList());
        return (long) countedList.size();
    }

    /**
     * Finds the Normalized Mean of a Stream of Integers.
     * @param stream the inputed Stream of Integers, can be empty
     * @return the normalized mean, if empty stream return 0.0 automatically
     */
    static double normalizedMean(Stream<Integer> stream) {
        List<Integer> streamList = stream
            .sorted()
            .collect(Collectors.toList());

        try {
            int max = streamList
                .get(streamList.size() - 1);
            int min = streamList
                .get(0);
            int size = streamList
                .size();
            int sum = streamList
                .stream()
                .reduce(0, (x, y) -> x + y);
            return Optional.<Double>of((double) ((sum / size) - min) / (max - min))
                .filter((x) -> !x.isNaN())
                .map((x) -> x)
                .orElseThrow();
        } catch (Exception e) {
            return 0.0;
        }
    }
}
