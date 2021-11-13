import java.util.stream.IntStream;
import java.util.ArrayList;

public class Main {
    public static double simulate(int seed, int n) {
        ArrayList<Double> result = new ArrayList<Double>();
        Circle circle = new Circle(new Point(0, 0), 1.0);
        Rand.randRange(seed, x -> (2.0) * x / (Integer.MAX_VALUE - 1) - 1.0)
            .limit(n * 2)
            .forEach(x -> result.add(x));
        long count = IntStream
            .iterate(0, x -> x + 2)
            .takeWhile(x -> x < result.size())
            .filter(x -> {
                Point point = new Point(result.get(x), result.get(x + 1));
                return circle.contains(point);
            })
            .count();
        return (double) count * 4 / n;
    }
}
