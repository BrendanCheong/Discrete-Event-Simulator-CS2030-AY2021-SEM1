import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Optional;

public class Main3 {

    public static Optional<MinMax> findMinMax(Stream<Integer> intstream) {
            Comparator<Integer> comparator = (x, y) -> x.compareTo(y);
            Optional<MinMax> answer = intstream
                .map((x) -> new MinMax(x, x))
                .reduce((a, b) -> new MinMax(
                // The min of the min elements
                comparator.compare(a.getMin(), b.getMin()) < 0 ? a.getMin() : b.getMin(),
                // The max of the max elements
                comparator.compare(a.getMax(), b.getMax()) > 0 ? a.getMax() : b.getMax()
                )
            );
            return answer;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("From range: ");
        int from = (sc).nextInt();
        System.out.print("To range: ");
        int to = (sc).nextInt();
        sc.close();

        Stream<Integer> intstream = IntStream
            .rangeClosed(from, to)
            .mapToObj((x) -> (Integer) x);

        System.out.println(findMinMax(intstream));
    }
}
