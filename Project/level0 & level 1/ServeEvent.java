import java.util.List;
import java.util.ArrayList;

public class ServeEvent extends Event implements MutateEvent {

    private static final String EVENT_NAME = "serves";
    private final Server selectedServer;

    public ServeEvent(Customer customer, List<Server> servers,
        Server selectedServer) {

        super(customer, servers);
        this.selectedServer = selectedServer;
    }

    public Event mutate() {
        Customer customer = super.getCustomer();
        List<Server> servers = super.getServers();

        double newTime = customer.getArrivalTime() + this.getDefaultServeTime();
        List<Customer> newWaitingList = new ArrayList<>();
        newWaitingList.add(customer);
        System.out.println("Serves mutates");
        Customer newCustomer = new Customer(customer.getCustomerID(), newTime);

        Server newServer = new Server(this.selectedServer.getId(), true,
            newWaitingList, newTime);

        servers.set(this.selectedServer.getId() - 1, newServer);

        return new DoneEvent(newCustomer, servers, this.selectedServer);
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

    @Override
    public String toString() {
        return super.getCustomer().toString() +
            String.format("%s by %s", EVENT_NAME, this.selectedServer.toString());
    }

}
