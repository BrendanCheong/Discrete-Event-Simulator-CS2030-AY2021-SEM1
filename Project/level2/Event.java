
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Abstract Event class to enforce polymorphism. subclasses can create the next
 * event and update statistics.
 */
public abstract class Event {

    private static final double DEFAULT_SERVE_TIME = 1.0;
    private final Customer customer;
    private final double time;
    private final List<Server> servers;

    /**
     * Event constructor for LeaveEvent.
     * 
     * @param customer customer that the event is acting on
     * @param servers  list of servers
     * @param time     time that the event is created
     */
    public Event(Customer customer, List<Server> servers, double time) {
        this.customer = customer;
        this.servers = servers;
        this.time = time;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public int getCustomerId() {
        return getCustomer().getCustomerId();
    }

    public double getTime() {
        return this.time;
    }

    public List<Server> getServers() {
        return this.servers;
    }

    public static double getDefaultServeTime() {
        return DEFAULT_SERVE_TIME;
    }

    protected final Server findNextAvailableServer() {
        // clone the servers first just in case of accidental mutation
        List<Server> servers = new ArrayList<Server>(this.servers);
        PriorityQueue<Server> waitingServers = 
            new PriorityQueue<>(100, new WaitingTimeComparator());

        for (Server server : servers) {
            if (server.canTakeServeEvent()) {
                return server;
            }
        }
        // not sure about this priorityQueue Algorithm but oh wells
        // return the server with the shortest Id

        for (Server server : servers) {
            if (server.canTakeWaitEvent()) {
                waitingServers.add(server);
            }
        }

        if (waitingServers.size() > 0) {
            return waitingServers.peek();
        }
        // Return a Server that is not available and cannot wait
        // a dummy server
        return new Server(69, false, true, 420.0);
    }

    public Server getServer() {
        return new Server(69, false, true, 420.0);
    }

    public abstract String getName();

    public Event mutate() {
        return new NullEvent(this.getCustomer(), this.getServers(), this.getTime());
    }
}
