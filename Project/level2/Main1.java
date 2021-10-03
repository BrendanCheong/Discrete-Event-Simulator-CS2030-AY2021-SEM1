// import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.ArrayList;

public class Main1 {
    /**
     * Takes in number of servers and arrival times to simulate restaurant.
     * <p>simulates the entire restaurant with simulate method</p>
     * @param args takes in an array of arrival times and number of servers
     **/
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
