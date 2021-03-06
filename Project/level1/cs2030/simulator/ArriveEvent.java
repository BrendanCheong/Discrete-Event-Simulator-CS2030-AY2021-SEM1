package cs2030.simulator;

import java.util.List;

/**
 * Arrive Event to show that the customer has arrived to the restaurant.
 * */
class ArriveEvent extends Event {

    private static final String EVENT_NAME = "arrives";

    public ArriveEvent(Customer customer, List<Server> servers, double time) {
        super(customer, servers, time);
    }

    /**
     * Method to execute the ArriveEvent.
     * <p> to decide which event to move on to, find out if you can take the
     * wait event, or the serve event based on what the server says </p>
     * <p> if not, then just leave </p>
     * @return the next event to be executed
     */
    public Event mutate() {
        // from the getFreeServer method, get a free server
        Server freeServer = this.findNextAvailableServer();
        Customer customer = this.getCustomer();
        List<Server> newServers = this.getServers();
        double time = this.getTime();

        if (freeServer.canTakeWaitEvent()) {
            return new WaitEvent(customer, newServers, freeServer, time);
        } else if (freeServer.canTakeServeEvent()) {
            return new ServeEvent(customer, newServers, freeServer, time);
        } else {
            return new LeaveEvent(customer, newServers, freeServer, time);
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
