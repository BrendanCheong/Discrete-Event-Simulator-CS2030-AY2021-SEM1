package cs2030.simulator;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class ServeEvent extends Event {
    
    public ServeEvent(Customer customer, String eventStatus, Statistics stats) {
        super(customer, eventStatus, stats);
    }

    public ServeEvent(Customer customer, Server server, 
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    public ServeEvent(Optional<Customer> customer, Optional<Server> server,
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    @Override
    public Optional<Event> mutate(List<Server> serverList) {
        
        // ? use my customer waitingTime
        // ? can server be null??
        Customer customerDone = super.getServerNotNull()
            .serve(super.getCustomerNotNull());
        
        double newWaitingTime = this.getCustomerNotNull().getWaitingTime();
        this.getStats().addTotalWaitingTime(newWaitingTime);

        Event doneEvent = new DoneEvent(customerDone, super.getServerNotNull(),
            "DONE", customerDone.getTime(), this.getStats());
        
        System.out.println(this.toString());
        return Optional.<Event>of(doneEvent);
    }
}
