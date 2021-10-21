package cs2030.simulator;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class LeaveEvent extends Event {
    
    public LeaveEvent(Customer customer, String eventStatus, Statistics stats) {
        super(customer, eventStatus, stats);
    }

    public LeaveEvent(Customer customer, Server server, 
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    public LeaveEvent(Optional<Customer> customer, Optional<Server> server,
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    @Override
    public Optional<Event> mutate(List<Server> serverList) {
        System.out.println(this.toString());

        return Optional.empty();
    }
}
