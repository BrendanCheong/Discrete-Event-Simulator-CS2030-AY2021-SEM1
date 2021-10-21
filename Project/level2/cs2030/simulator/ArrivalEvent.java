package cs2030.simulator;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

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

        for (Server server : serverList) {
            if (server.isIdle()) {
                Event servedEvent = new ServeEvent(super.getCustomerNotNull().setServed(),
                    server, "SERVE", super.getTime(), this.getStats());
                outputEvent = Optional.<Event>of(servedEvent);
                serveCondition = true;
                break;
            }
        }

        if(serveCondition == false) {
            for (Server server : serverList) {
                if (server.isFull() == false) {
                    Event waitEvent = new WaitEvent(super.getCustomerNotNull().setWait(),
                        server, "WAIT", super.getTime(), this.getStats());
                    outputEvent = Optional.<Event>of(waitEvent);
                    serveCondition = true;
                    break;
                }
            }
        }

        if (serveCondition) {
            this.getStats().addServedCustomersByOne();
        } else {
            this.getStats().addLeftCustomersByOne();
            Optional<Customer> customer = Optional.<Customer>of(super.getCustomerNotNull().setLeave());
            Event leftEvent = new LeaveEvent(customer, super.getServer(), 
                "LEAVE", super.getTime(), this.getStats());
            
            outputEvent = Optional.<Event>of(leftEvent);
        }
        return outputEvent;
    }
}
