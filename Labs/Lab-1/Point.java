public class Point {

    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the Point inbetween the current point and the input point.
     * @param p takes in the input point
     * @return the midpoint as a Point instance
     */
    public Point midPoint(Point p) {
        double coordinateX = (this.x + p.x) / 2;
        double coordinateY = (this.y + p.y) / 2;
        Point midPoint = new Point(coordinateX, coordinateY);
        return midPoint;
    }

    /**
     * Gets the distance from a point to another point as double.
     * @param p is the target point to be calculated against
     * @return the distance between the current point against target point
     *
     */
    public double distanceTo(Point p) {
        double distanceX = Math.abs(this.x - p.x);
        double distanceY = Math.abs(this.y - p.y);
        return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }

    /**
     * Gets the angle between the current Point and the input point.
     * uses the arctan java Math method
     * @param p takes in the input point
     * @return angle between current to input point in radians
     */
    public double angleTo(Point p) {
        double coordinateX = p.x - this.x;
        double coordinateY = p.y - this.y;
        return Math.atan2(coordinateY, coordinateX);
    }

    /**
     * Moves the current point to a new point based on angle and distance.
     * @param theta takes in a double radian
     * @param d takes in a double distance d
     * @return the new moved point after taking the inputs
     */

    public Point moveTo(double theta, double d) {
        double coordinateX = this.x + (d * Math.cos(theta));
        double coordinateY = this.y + (d * Math.sin(theta));
        return new Point(coordinateX, coordinateY);
    }

    @Override
    public String toString() {
        String coordinateX = String.format("%.3f", this.x);
        String coordinateY = String.format("%.3f", this.y);
        return "point (" + coordinateX + ", " + coordinateY + ")";
    }
}
