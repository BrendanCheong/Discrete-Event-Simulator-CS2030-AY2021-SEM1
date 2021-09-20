import java.util.List;
import java.util.ArrayList;

public class ArriveEvent extends Event implements MutateEvent {

    private static final String EVENT_NAME = "arrives";

    public ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
    }

    public Event mutate() {
        List<Server> servers = new ArrayList<>(super.getServers());
        Server availableServer = this.findNextAvailableServer();
        System.out.println("arrives mutates");
        Customer customer = super.getCustomer();
        if (availableServer == null) {
            return new LeaveEvent(customer, servers, availableServer);
        } else if (availableServer.canServeCustomerNow()) {
            return new ServeEvent(customer, servers, availableServer);
        } else if (availableServer.canTakeWaitingCustomer()) {
            return new WaitEvent(customer, servers, availableServer);
        } else {
            System.out.print("System Bug!");
            return null;
        }
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

    @Override
    public String toString() {
        return super.getCustomer().toString() + EVENT_NAME;
    }

}
