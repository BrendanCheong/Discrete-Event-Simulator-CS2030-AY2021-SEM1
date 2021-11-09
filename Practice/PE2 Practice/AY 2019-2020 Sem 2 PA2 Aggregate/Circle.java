public class Circle {

    private static int numOfCircles = 0;
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
        numOfCircles += 1;
    }

    double getRadius() {
        return this.radius;
    }

    static int getNumberOfCircles() {
        return numOfCircles;
    }

    public String toString() {
        return String.format("Circle of radius %.1f", getRadius());
    }
}
