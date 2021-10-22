package cs2030.simulator;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ArrivalEvent extends Event {
    
    public ArrivalEvent(Customer customer, String eventStatus, Statistics stats) {
        super(customer, eventStatus, stats);
    }

    public ArrivalEvent(Customer customer, Server server, 
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    public ArrivalEvent(Optional<Customer> customer, Optional<Server> server,
        String eventStatus, double time, Statistics stats) {
        super(customer, server, eventStatus, time, stats);
    }

    @Override
    public Optional<Event> mutate(List<Server> serverList) {
        // proccessArrivalEvent
        boolean serveCondition = false;
        Optional<Event> outputEvent = Optional.empty();
        System.out.println(this.toString());

        outputEvent = serverList
            .stream()
            .filter((server) -> server.isIdle())
            .map((server) -> {
                Event servedEvent = new ServeEvent(super.getCustomerNotNull().setServed(),
                    server, "SERVE", super.getTime(), this.getStats());
                return servedEvent;
            })
            .findFirst();

        serveCondition = serverList
            .stream()
            .anyMatch((server) -> server.isIdle());

        if (serveCondition == false) {
            outputEvent = serverList
                .stream()
                .filter((server) -> !server.isFull()) // wait event if the server has queue space
                .map((server) -> {
                    Event waitEvent = new WaitEvent(super.getCustomerNotNull().setWait(),
                        server, "WAIT", super.getTime(), this.getStats());
                    return waitEvent;
                })
                .findFirst();

            serveCondition = serverList
                .stream()
                .anyMatch((server) -> !server.isFull());
        }

        if (serveCondition) {
            this.getStats().addServedCustomersByOne();
        } else {
            this.getStats().addLeftCustomersByOne(); // if not then just leave
            Optional<Customer> customer = 
                Optional.<Customer>of(super.getCustomerNotNull().setLeave());
            Event leftEvent = new LeaveEvent(customer, super.getServer(), 
                "LEAVE", super.getTime(), this.getStats());
            
            outputEvent = Optional.<Event>of(leftEvent);
        }
        return outputEvent;
    }
}
