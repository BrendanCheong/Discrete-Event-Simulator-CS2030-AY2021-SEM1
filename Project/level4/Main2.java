import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.List;
import java.util.ArrayList;

public class Main2 {
    /**
     * Takes in number of servers and arrival times to simulate restaurant.
     * <p>simulates the entire restaurant with simulate method</p>
     * @param args takes in an array of arrival times and number of servers
     **/
    public static void main(String[] args) {
        int levelStatus = 2;
        Scanner sc = new Scanner(System.in);
        List<Double> timeArray = new ArrayList<>();
        List<Double> serveTimeArray = new ArrayList<>();
        LinkedList<Double> restTimeArray = new LinkedList<>();
        LinkedList<Double> selfCheckOutRestArray = new LinkedList<>();

        int numServers = sc.nextInt();
        int queueAmount = sc.nextInt();

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            timeArray.add(arrivalTime);
            double serveTime = sc.nextDouble();
            serveTimeArray.add(serveTime);
        }

        int numberOfCustomers = timeArray.size();
        int numberOfSelfCheckoutCounters = 0;

        IntStream
            .range(0, numberOfCustomers)
            .forEach((x) -> {
                restTimeArray.add(0.00);
                selfCheckOutRestArray.add(0.00);
            });

        Simulator simulator = new Simulator(numServers, timeArray, numberOfCustomers, levelStatus,
            queueAmount, serveTimeArray, restTimeArray, numberOfSelfCheckoutCounters,
            selfCheckOutRestArray);

        simulator.simulate();

        sc.close();
    }
}