package cs2030.simulator;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class WaitEvent extends Event {
    
    public WaitEvent(Customer customer, String eventStatus, Statistics stats) {
        super(customer, eventStatus, stats);
    }

    public WaitEvent(Customer customer, Server server, 
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    public WaitEvent(Optional<Customer> customer, Optional<Server> server,
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    @Override
    public Optional<Event> mutate(List<Server> serverList) {
        System.out.println(this.toString());

        // instead of adding to EventQueue, you change previous Event's Server customer queue
        this.proccessWait(); 
        return Optional.empty();
    }
}
