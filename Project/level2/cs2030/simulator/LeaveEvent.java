package cs2030.simulator;

import java.util.List;

class LeaveEvent extends Event {

    private static final String EVENT_NAME = "leaves";
    private final Server server;

    /**
     * Creates an LeaveEvent.
     * 
     * @param customer customer that the event is acting on
     * @param time     time that the event is created
     */
    public LeaveEvent(Customer customer, List<Server> servers, Server server, double time) {
        super(customer, servers, time);
        this.server = server;
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
