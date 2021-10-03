package cs2030.simulator;

import java.util.List;

/**
 * NullEvent to signal the end of a customer getting served.
 */
class NullEvent extends Event {

    private static final String EVENT_NAME = "Null";

    /**
     * Creates a NullEvent.
     * <p> a Null event is an event that returns a non-abstract Event class </p>
     * <p> Since every Event needs to mutate </p>
     * @param customer customer that the event is involving
     * @param time     time at which event is created
     * @param server   server that the NullEvent belongs to
     **/
    public NullEvent(Customer customer, List<Server> servers, double time) {
        super(customer, servers, time);
    }

    /**
     * Mutates to the next event.
     * <p> in this case, the next event is a nother Null event </p>
     * <p> Since a null event shouldn't be created again in the simulation process
     * check if theres any bugs in the simulation if there is an excessive amount
     * of Null Events in the eventQueue list </p>
     **/
    @Override
    public Event mutate() {
        return new NullEvent(this.getCustomer(), this.getServers(), this.getTime());
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

}
