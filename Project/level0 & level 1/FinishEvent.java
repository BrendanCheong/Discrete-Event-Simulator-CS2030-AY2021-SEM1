import java.util.List;

public class FinishEvent extends Event implements MutateEvent {

    private static final String EVENT_NAME = "Finish Event";
    private final Server selectedServer;

    public FinishEvent(Customer customer, List<Server> servers,
        Server selectedServer) {

        super(customer, servers);
        this.selectedServer = selectedServer;
    }

    public Event mutate() {
        return null;
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

    @Override
    public String toString() {
        return "Finish Event";
    }
}
