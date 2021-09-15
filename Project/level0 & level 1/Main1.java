import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Main {
    /**
     * Main method to create a Discrete Event Simulator.
     * <p>Will first take in number of servers, then timing of customers</p>
     * <p>Then create and execute the Simulated Event</p>
     * @param args arguments to enter into command line, will end after no input
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> arrivalTimes = new ArrayList<Double>();

        // take in the number of servers
        int serverAmount = sc.nextInt();

        while (sc.hasNextDouble()) {
            arrivalTimes.add(sc.nextDouble());
        }
        Simulator s = new Simulator(serverAmount, arrivalTimes);
        s.simulate();
        sc.close();
    }
}
