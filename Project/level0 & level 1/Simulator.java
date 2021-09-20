import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private final int numberOfServers;
    private final ArrayList<Double> arrivalTimeArray;

    public Simulator(int numberOfServers, ArrayList<Double> arrivalTimeArray) {
        this.numberOfServers = numberOfServers;
        this.arrivalTimeArray = arrivalTimeArray;
    }

    public void simulate() {
        PriorityQueue<Event> eventQueue = new PriorityQueue<>(1000, new EventComparator());
        List<Event> allEvents = new ArrayList<>(); // outsource allEvents to Statistic class later
        List<Server> servers = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        int numOfLeft = 0;
        int numOfServed = 0;

        for (int id = 1; id <= arrivalTimeArray.size(); ++id) {
            Customer customer = new Customer(id, arrivalTimeArray.get(id - 1));
            customers.add(customer);
        }

        for (int id = 1; id <= numberOfServers; ++id) {
            Server server = new Server(id, false, new ArrayList<Customer>(), 0.0);
            servers.add(server);
        }

        for (Customer customer : customers) {
            eventQueue.add(new ArriveEvent(customer, servers));
        }

        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.poll();
            System.out.println(event);

            Event nextEvent = event.mutate();

            switch(event.getName()) {
                case("arrives"):
                    eventQueue.add(nextEvent);
                    break;
                case("waits"):
                    eventQueue.add(nextEvent);
                    break;
                case("serves"):
                    eventQueue.add(nextEvent);
                    break;
                case("done"):
                    numOfServed += 1;
                    break;
                default:
                    numOfLeft += 1;
            }
        }
        String finalStrike = String.format("number served: %d, number left: %d",
            numOfServed, numOfLeft);
        System.out.println(finalStrike);
    }
}
