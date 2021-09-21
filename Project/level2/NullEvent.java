
import java.util.List;

/**
 * NullEvent to signal the end of a customer getting served.
 */
class NullEvent extends Event {

    private static final String EVENT_NAME = "Null";

    /**
     * Creates a NullEvent.
     * 
     * @param customer customer that the event is involving
     * @param time     time at which event is created
     * @param server   server that the NullEvent belongs to
     */
    public NullEvent(Customer customer, List<Server> servers, double time) {
        super(customer, servers, time);
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

}
