
import java.util.List;

/**
 * ServeEvent class to simulate the start of service to a customer by a server.
 */
class ServeEvent extends Event {

    private final static String EVENT_NAME = "serves";
    private final Server server;

    /**
     * Creates a ServeEvent.
     * 
     * @param customer customer that the event is involving
     * @param time     time at which event is created
     * @param server   server that the ServedEvent belongs to
     */
    public ServeEvent(Customer customer, List<Server> servers, Server server, double time) {
        super(customer, servers, time);
        this.server = server;
    }

    public Event mutate() {
        Customer customer = super.getCustomer();

        List<Server> newServers = super.getServers();
        // Server server = this.getServer();
        double newTime = super.getTime() + super.getDefaultServeTime();

        Server updatedServer = new Server(server.getServerId(), false, false, newTime);

        newServers.set(server.getServerId() - 1, updatedServer);

        return new DoneEvent(customer, newServers, server, newTime);
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

    @Override
    public Server getServer() {
        return this.server;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d %s by %s", this.getTime(), this.getCustomerId(), this.getName(),
                this.server.toString());
    }
}
