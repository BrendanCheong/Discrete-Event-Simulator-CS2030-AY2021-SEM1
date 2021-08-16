public class Circle {

    private final Point centre;
    private final double radius;

    public Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public Point getCentre() {
        return this.centre;
    }

    public double getRadius() {
        return this.radius;
    }

    public Circle getCircle(Point centre, double radius) {
        return new Circle(centre, radius);
    }

    @Override
    public String toString() {
        return String.format("Circle of radius %.3f and centre of %s", getCentre(), getRadius());
    }

}
