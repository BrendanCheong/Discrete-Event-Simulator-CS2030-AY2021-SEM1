import java.util.Scanner;

class Main {

    private static Point[] clientPoints;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of Points to calculate: ");

        int pointAmt = sc.nextInt();
        clientPoints = new Point[pointAmt];

        for (int i = 0; i < pointAmt; ++i) {
            String pointNumber = String.format("Point %d: ", i + 1);
            System.out.println("Please enter the x coordinate of " + pointNumber);
            double coordinateX = sc.nextDouble();
            System.out.println("Please enter the y coordinate of " + pointNumber);
            double coordinateY = sc.nextDouble();
            clientPoints[i] = new Point(coordinateX, coordinateY);
        }

        for (Point points: clientPoints) {
            System.out.println(points.toString());
        }
    }

}
