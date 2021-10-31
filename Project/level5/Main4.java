import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Main4 {
    /**
     * Takes in number of servers and arrival times to simulate restaurant.
     * <p>simulates the entire restaurant with simulate method</p>
     * @param args takes in an array of arrival times and number of servers
     **/
    public static void main(String[] args) {
        int levelStatus = 4;
        Scanner sc = new Scanner(System.in);
        List<Double> timeArray = new ArrayList<>(100);
        List<Double> serveTimeArray = new ArrayList<>(100);
        LinkedList<Double> restTimeArray = new LinkedList<>();

        int numServers = sc.nextInt();
        int numberOfSelfCheckoutCounters = sc.nextInt();
        int queueAmount = sc.nextInt();
        int numberOfCustomers = sc.nextInt();

        while (sc.hasNextDouble()) {
            if (numberOfCustomers > 0) {
                double arrivalTime = sc.nextDouble();
                timeArray.add(arrivalTime);
                double serveTime = sc.nextDouble();
                serveTimeArray.add(serveTime);
                --numberOfCustomers;
            } else {
                restTimeArray.add(sc.nextDouble());
            }
        }

        numberOfCustomers = timeArray.size();

        Simulator simulator = new Simulator(numServers, timeArray, numberOfCustomers, 
        levelStatus, queueAmount, serveTimeArray, restTimeArray, 
        numberOfSelfCheckoutCounters,  0, 0.000, 0.000, 0.000,
        0.000, 0.000);

        simulator.simulate();

        sc.close();
    }
}
