import java.util.List;
import java.util.ArrayList;

public class WaitEvent extends Event implements MutateEvent {

    private static final String EVENT_NAME = "waits";
    private final Server selectedServer;

    public WaitEvent(Customer customer, List<Server> servers,
        Server selectedServer) {

        super(customer, servers);
        this.selectedServer = selectedServer;
    }

    public Event mutate() {
        Customer customer = super.getCustomer();
        List<Server> servers = super.getServers();

        double time = customer.getArrivalTime();
        double nextAvailableTime = this.selectedServer.getNextAvailableTime();

        Customer newCustomer = new Customer(customer.getCustomerID(),
            nextAvailableTime);

        if (this.selectedServer.canTakeWaitingCustomer()) {
            List<Customer> newWaitingList = new ArrayList<>();
            newWaitingList.add(customer);

            Server newServer = new Server(this.selectedServer.getId(), true,
                newWaitingList, time);

            servers.set(this.selectedServer.getId() - 1, newServer);

            return new ServeEvent(newCustomer, servers, this.selectedServer);
        } else {
            return new LeaveEvent(customer, servers, this.selectedServer);
        }
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

    @Override
    public String toString() {
        return super.getCustomer().toString() +
            String.format("%s at %s", EVENT_NAME, this.selectedServer.toString());
    }

}
