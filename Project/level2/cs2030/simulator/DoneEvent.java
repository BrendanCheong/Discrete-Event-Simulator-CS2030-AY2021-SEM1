package cs2030.simulator;

import java.util.List;

/**
 * DoneEvent to simulate the completion of service to a customer by a server.
 */
public class DoneEvent extends Event {

    private static final String EVENT_NAME = "done";
    private final Server server;

    public DoneEvent(Customer customer, List<Server> servers, Server server, double time) {
        super(customer, servers, time);
        this.server = server;
    }

    /**
     * Method to execute the DoneEvent.
     * 
     * @return the next event to be executed in this case, return dummy NullEvent
     */
    public Event mutate() {
        Customer customer = super.getCustomer();
        List<Server> newServers = super.getServers();
        // Server server = super.getServer();
        double time = super.getTime();

        if (server.getHasWaitingCustomer()) {
            double newTime = super.getTime() + super.getDefaultServeTime();

            Server updatedServer = new Server(server.getServerId(), true, false, newTime);

            newServers.set(server.getServerId() - 1, updatedServer);
            return new NullEvent(customer, newServers, time);

        } else {
            Server updatedServer = new Server(server.getServerId(), true, false, time);

            newServers.set(server.getServerId() - 1, updatedServer);
            return new NullEvent(customer, newServers, time);
        }
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

    @Override
    public Server getServer() {
        return this.server;
    }

    /**
     * Method to get DoneEvent string when it is instantiated.
     * 
     * @return string of the DoneEvent
     */
    public String toString() {
        return String.format("%.3f %d %s serving by %s", 
            this.getTime(), this.getCustomerId(), 
            this.getName(),this.server.toString());
    }
}
