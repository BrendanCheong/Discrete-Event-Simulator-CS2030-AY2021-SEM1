public class Point {

    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String coordinateX = String.format("%.3f", getX());
        String coordinateY = String.format("%.3f", getY());
        return "(" + coordinateX + ", " + coordinateY + ")";
    }

}
