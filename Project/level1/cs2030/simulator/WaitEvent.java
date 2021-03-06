package cs2030.simulator;

import java.util.List;

/**
 * WaitEvent class to simulate the act of customer waiting.
 */
class WaitEvent extends Event {

    private static final String EVENT_NAME = "waits";
    private final Server server;

    public WaitEvent(Customer customer, List<Server> servers, Server server, double time) {
        super(customer, servers, time);
        this.server = server;
    }

    /**
     * Method to execute the Event.
     * <p>If server can take a wait event, create a serve event with a server who is serving
     * a customer and can take a waiting customer</p>
     * <p>If not, then the customer will leave restaurant T.T </p>
     * @return Serve or Leave Event depending if server can serve or not
     */
    public Event mutate() {
        Customer customer = this.getCustomer();
        List<Server> newServers = this.getServers();
        double time = this.getTime();
        double nextAvailableTime = this.server.getNextAvailableTime();

        if (server.canTakeWaitEvent()) {
            Server updatedServer = new Server(server.getServerId(), false, true, time);

            newServers.set(server.getServerId() - 1, updatedServer);

            return new ServeEvent(customer, newServers, server, nextAvailableTime);

        } else {
            return new LeaveEvent(customer, newServers, server, time);
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
     * Method to return string of WaitEvent when instantiated.
     *
     * @return returns string of WaitEvent
     */
    public String toString() {
        return super.getCustomer().toString() +
            String.format("%s at %s", EVENT_NAME, this.server.toString());
    }

}
