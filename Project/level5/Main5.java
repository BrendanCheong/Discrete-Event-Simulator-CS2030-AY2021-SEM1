import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Main5 {
    /**
     * Takes in number of servers and arrival times to simulate restaurant.
     * <p>simulates the entire restaurant with simulate method</p>
     * @param args takes in an array of arrival times and number of servers
     **/
    public static void main(String[] args) {
        int levelStatus = 5;
        List<Double> timeArray = new ArrayList<>(100);
        List<Double> serveTimeArray = new ArrayList<>(100);
        LinkedList<Double> restTimeArray = new LinkedList<>();
    
        Scanner sc = new Scanner(System.in);
        int seed = sc.nextInt();
        int numberOfServers = sc.nextInt();
        int numberOfSelfCheckout = sc.nextInt();
        int queueAmount = sc.nextInt();
        int numberOfCustomers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restingRate = sc.nextDouble();
        double restingProbability = sc.nextDouble();
        double greedyCustomerProbability = sc.nextDouble();
    
        Simulator simulator = new Simulator(numberOfServers, timeArray, 
            numberOfCustomers, levelStatus, queueAmount, serveTimeArray, 
            restTimeArray, numberOfSelfCheckout, seed, arrivalRate, 
            serviceRate, restingRate, restingProbability, 
            greedyCustomerProbability);
    
        simulator.simulate();
    
        sc.close();
    }
}
