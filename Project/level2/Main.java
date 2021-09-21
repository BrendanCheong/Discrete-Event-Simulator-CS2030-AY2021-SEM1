
import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> timeArray = new ArrayList<>();

        int numServers = sc.nextInt();

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            timeArray.add(arrivalTime);
        }

        Simulator simulator = new Simulator(numServers, timeArray);
        simulator.simulate();
        sc.close();
    }

}
