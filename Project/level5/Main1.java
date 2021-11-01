import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.List;
import java.util.ArrayList;

public class Main1 {

    private static final int levelStatus = 1;
    
    /**
     * Takes in number of servers and arrival times to simulate restaurant.
     * <p>simulates the entire restaurant with simulate method</p>
     * @param args takes in an array of arrival times and number of servers
     **/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> timeArray = new ArrayList<>();
        List<Double> serveTimeArray = new ArrayList<>();
        LinkedList<Double> restTimeArray = new LinkedList<>();

        int numServers = sc.nextInt();

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            timeArray.add(arrivalTime);
            serveTimeArray.add(1.000); // default servetime is 1.000
        }

        int numberOfCustomers = timeArray.size();
        int numberOfSelfCheckoutCounters = 0;

        IntStream
            .range(0, numberOfCustomers)
            .forEach((x) -> {
                restTimeArray.add(0.00);
            });

        Simulator simulator = new Simulator(numServers, timeArray, numberOfCustomers, levelStatus,
            1, serveTimeArray, restTimeArray, numberOfSelfCheckoutCounters, 
            0, 0.000, 0.000, 0.000, 0.000, 0.000); // default queueAmount for waitingCustomers is 1
        
        simulator.simulate();

        sc.close();
    }
}
