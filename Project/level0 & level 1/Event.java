import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public abstract class Event {

    private static final double DEFAULT_SERVE_TIME = 1.0;
    private final Customer customer;
    private final List<Server> servers;

    public Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = servers;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public List<Server> getServers() {
        return this.servers;
    }

    protected final double getDefaultServeTime() {
        return this.DEFAULT_SERVE_TIME;
    }

    protected final Server findNextAvailableServer() {
        // clone the servers first just in case of accidental mutation
        List<Server> servers = new ArrayList<Server>(this.servers);
        PriorityQueue<Server> waitingServers = new PriorityQueue<>(100,
            new WaitingTimeComparator());

        for (Server server : servers) {
            if (server.canServeCustomerNow()) {
                return server;
            }
        }
        // not sure about this priorityQueue Algorithm but oh wells
        // return the server with the shortest waitingTime
        for (Server server : servers) {
            if (server.canTakeWaitingCustomer()) {
                waitingServers.add(server);
            }
        }

        if (waitingServers.size() > 0) {
            return waitingServers.peek();
        }

        return null;
    }

    public abstract Event mutate();

    public abstract String getName();

    public double getServedTime() {
        return customer.getArrivalTime() + DEFAULT_SERVE_TIME;
    }

}
