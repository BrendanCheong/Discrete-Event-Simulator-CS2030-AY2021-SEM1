class Circle {
    private final Point centre;
    private final double radius;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    boolean contains(Point p) {
        return this.centre.distanceTo(p) < this.radius;
    }
}
