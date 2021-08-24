Circle createUnitCircle(Point p, Point q) {
    double unitCircleRadius = 1.0;
    Point midPoint = p.midPoint(q);
    double midDistanceToQ = midPoint.distanceTo(q);
    double distanceD = Math.sqrt(unitCircleRadius - Math.pow(midDistanceToQ, 2));
    double theta = p.angleTo(q) + Math.PI / 2;

    Point centre = midPoint.moveTo(theta, distanceD);
    return new Circle(centre, unitCircleRadius);
}
