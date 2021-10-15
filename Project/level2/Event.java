package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Abstract Event class to enforce polymorphism. subclasses can create the next
 * event and update statistics.
 */
abstract class Event {

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

    /**
     * Abstraction to find the next available Server for arrive events to use.
     * @return the next best server after arriving to the restaurant
     * */
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
        // PriorityQueue algo will find the best server for the waiting customer
        // which is in this case, the lowest Id server
        // why its not the server with the next lowest serve time is anyone's guess man

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
        // helps other subclasses return a dummy server
        return new Server(69, false, true, 420.0);
    }

    public abstract String getName();

    public abstract Event mutate();
}
