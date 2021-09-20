import java.util.List;
import java.util.ArrayList;

public class LeaveEvent extends Event implements MutateEvent {

    private static final String EVENT_NAME = "leaves";
    private final Server selectedServer;

    public LeaveEvent(Customer customer, List<Server> servers,
        Server selectedServer) {

        super(customer, servers);
        this.selectedServer = selectedServer;
    }

    public Event mutate() {
        Customer customer = super.getCustomer();
        double time = customer.getArrivalTime();

        return new FinishEvent(customer, new ArrayList<Server>(),
            this.selectedServer);
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
