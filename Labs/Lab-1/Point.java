public class Point {

    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private double getX() {
        return this.x;
    }

    private double getY() {
        return this.y;
    }

    /**
     * Returns the Point inbetween the current point and the input point.
     * @param p takes in the input point
     * @return the midpoint as a Point instance
     */
    public Point midPoint(Point p) {
        double coordinateX = (getX() + p.getX()) / 2;
        double coordinateY = (getY() + p.getY()) / 2;
        Point midPoint = new Point(coordinateX, coordinateY);
        return midPoint;
    }

    /**
     * Gets the angle between the current Point and the input point.
     * uses the arctan java Math method
     * @param p takes in the input point
     * @return angle between current to input point in radians
     */
    public double pointAngle(Point p) {
        double coordinateX = p.getX() - getX();
        double coordinateY = p.getY() - getY();
        return Math.atan2(coordinateY, coordinateX);
    }

    @Override
    public String toString() {
        String coordinateX = String.format("%.3f", getX());
        String coordinateY = String.format("%.3f", getY());
        return "(" + coordinateX + ", " + coordinateY + ")";
    }

}
