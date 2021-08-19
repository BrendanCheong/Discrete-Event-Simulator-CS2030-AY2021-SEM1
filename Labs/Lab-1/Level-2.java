import java.util.Scanner;

class Main {

    private static Point[] clientPoints;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of Points you wish to calculate: ");
        int pointAmt = sc.nextInt();
        clientPoints = new Point[pointAmt];

        for (int i = 0; i < pointAmt; ++i) {
            String pointIndex = String.format("Point %d:", i + 1);
            System.out.println("Please enter the x coordinates for " + pointIndex);
            double coordinateX = sc.nextDouble();
            System.out.println("Please enter the y coordinates for " + pointIndex);
            double coordinateY = sc.nextDouble();

            clientPoints[i] = new Point(coordinateX, coordinateY);
        }

        for (int i = 0; i < clientPoints.length; ++i) {
            if ((i + 1) == clientPoints.length) {
                break;
            }
            Point p = clientPoints[i];
            Point q = clientPoints[i + 1];
            Point midPoint = p.midPoint(q);
            double angle = p.pointAngle(q);
            System.out.println(p.toString() + " and " + q.toString() +
                " has mid-point " + midPoint.toString() +
                " and angle " + angle);
        }
    }

}
