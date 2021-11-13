import java.util.stream.Stream;
import java.util.ArrayList;

class Main {

    public static double simulate(int seed, int n) {
        ArrayList<Double> res = new ArrayList<Double>();
        Circle circle = new Circle(new Point(0, 0), 1.0);
        int count = 0;
        double lo = -1.0;
        double hi = 1.0;
        Rand.randRange(seed, x -> (hi - lo) * x / (Integer.MAX_VALUE - 1) + lo)
            .limit(n * 2).forEach(x -> res.add(x));
        for (int i = 0; i < res.size(); i = i + 2) {
            Point temp = new Point(res.get(i), res.get(i + 1));
            if (circle.contains(temp)) {
                count++;
            }
        }
        return (double) count * 4 / n;
    }
}
