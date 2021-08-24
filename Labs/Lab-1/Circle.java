public class Circle {

    private final Point centre;
    private final double radius;

    public Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    private Point getCentre() {
        return this.centre;
    }

    private double getRadius() {
        return this.radius;
    }

    private Circle getCircle(Point centre, double radius) {
        return new Circle(centre, radius);
    }

    @Override
    public String toString() {
        return String.format("circle of radius %.1f centered at ", getRadius()) +
            getCentre().toString();
    }

}
