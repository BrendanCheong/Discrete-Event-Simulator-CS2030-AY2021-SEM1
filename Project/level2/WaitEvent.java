
import java.util.List;

/**
 * WaitEvent class to simulate the act of customer waiting.
 */
public class WaitEvent extends Event {

    private final static String EVENT_NAME = "waits";
    private final Server server;

    public WaitEvent(Customer customer, List<Server> servers, Server server, double time) {
        super(customer, servers, time);
        this.server = server;
    }

    /**
     * Method to execute the Event.
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

    /**
     * Method to return string of WaitEvent when instantiated.
     * 
     * @return returns string of WaitEvent
     */
    public String toString() {
        return super.getCustomer().toString() + String.format("%s at %s", EVENT_NAME, this.server.toString());
    }

}
