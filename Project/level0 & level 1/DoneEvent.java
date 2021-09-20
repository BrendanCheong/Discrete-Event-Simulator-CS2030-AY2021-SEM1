import java.util.List;
import java.util.ArrayList;

public class DoneEvent extends Event implements MutateEvent {

    private static final String EVENT_NAME = "done";
    private final Server selectedServer;

    public DoneEvent(Customer customer, List<Server> servers,
        Server selectedServer) {

        super(customer, servers);
        this.selectedServer = selectedServer;
    }

    public Event mutate() {
        Customer customer = super.getCustomer();
        List<Server> servers = super.getServers();
        double time = customer.getArrivalTime();

        if (this.selectedServer.hasWaitingCustomer()) {
            double newTime = customer.getArrivalTime() + super.getDefaultServeTime();

            Server newServer = new Server(selectedServer.getId(), false,
                new ArrayList<Customer>(), newTime);

            servers.set(this.selectedServer.getId() - 1, newServer);

            return new FinishEvent(customer, servers, this.selectedServer);
         } else {
            Server newServer = new Server(selectedServer.getId(), false,
                new ArrayList<Customer>(), time);

            servers.set(selectedServer.getId() - 1, newServer);

            return new FinishEvent(customer, servers, this.selectedServer);
         }
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

    @Override
    public String toString() {
        return super.getCustomer().toString() +
          String.format("%s serving by %s",
          EVENT_NAME, this.selectedServer.toString());
    }

}
