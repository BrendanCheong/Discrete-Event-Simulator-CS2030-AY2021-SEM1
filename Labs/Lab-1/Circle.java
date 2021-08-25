public class Circle {

    private final Point centre;
    private final double radius;

    public Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public boolean contains(Point p) {
        return p.distanceTo(this.centre) <= this.radius;
    }

    @Override
    public String toString() {
        return String.format("circle of radius %.1f centered at ", this.radius) +
            this.centre.toString();
    }

}
