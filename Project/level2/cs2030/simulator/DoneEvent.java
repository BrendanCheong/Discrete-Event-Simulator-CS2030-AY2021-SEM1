package cs2030.simulator;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class DoneEvent extends Event {
    
    public DoneEvent(Customer customer, String eventStatus, Statistics stats) {
        super(customer, eventStatus, stats);
    }

    public DoneEvent(Customer customer, Server server, 
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    public DoneEvent(Optional<Customer> customer, Optional<Server> server,
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    @Override
    public Optional<Event> mutate(List<Server> serverList) {
        System.out.println(this.toString());

        Optional<Event> newEvent = this.processDone();

        if (newEvent.isPresent()) {
            return newEvent;
        }
        return Optional.empty();
    }

}
