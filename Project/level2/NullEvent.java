

import java.util.List;

/**
 * NullEvent to signal the end of a customer getting served.
 */
public class NullEvent extends Event {

    private static final String EVENT_NAME = "Null";

    /**
     * Creates a NullEvent.
     * I still need a Null Event for the Simulator to count the number of events
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
