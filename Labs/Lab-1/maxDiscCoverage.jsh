Circle createUnitCircle(Point p, Point q) {
    double UNIT_CIRCLE_RADIUS = 1.0;
    Point midPoint = p.midPoint(q);
    double midDistanceToQ = midPoint.distanceTo(q);
    double distanceD = Math.sqrt(UNIT_CIRCLE_RADIUS - Math.pow(midDistanceToQ, 2));
    double theta = p.angleTo(q) + Math.PI / 2;

    Point centre = midPoint.moveTo(theta, distanceD);
    return new Circle(centre, UNIT_CIRCLE_RADIUS);
}

int findMaxDiscCoverage(Point[] points) {
    int maxDiscCoverage = 0;

    for (int i = 0; i < points.length - 1; i++) {
        for (int j = i + 1; j < points.length; j++) {
            Point p = points[i];
            Point q = points[j];
            Circle unitCircle = createUnitCircle(p, q);
            int totalPointsInCircle = 0;

            if (p.distanceTo(q) > 2 || p.distanceTo(q) == 0) {
                continue;
            }

            // test each point against this unit circle
            for (Point point: points) {
                if (unitCircle.contains(point)) {
                    totalPointsInCircle++;
                }
            }
            maxDiscCoverage = Math.max(maxDiscCoverage, totalPointsInCircle);
        }
    }

    return maxDiscCoverage;
}
