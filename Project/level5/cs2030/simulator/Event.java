package cs2030.simulator;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;

public class Event {

    private final Optional<Customer> customer;
    private final Optional<Server> server;
    private final String eventStatus;
    private final double time;

    public Event(Customer customer, String eventStatus) {
        // by default, the event has no server allocated as we do not know what kind of event it is
        this.customer = Optional.<Customer>of(customer);
        this.server = Optional.empty();
        this.eventStatus = eventStatus;
        this.time = customer.getArrivalTime();
    }

    public Event(Customer customer, Server server, 
        String eventStatus, double time) {
        this.customer = Optional.<Customer>of(customer);
        this.server = Optional.<Server>of(server);
        this.eventStatus = eventStatus;
        this.time = time;
    }

    public Event(Optional<Customer> customer, Optional<Server> server,
        String eventStatus, double time) {
        this.customer = customer;
        this.server = server;
        this.eventStatus = eventStatus;
        this.time = time;
    }

    public Optional<Customer> getCustomer() {
        return this.customer;
    }

    public Optional<Server> getServer() {
        return this.server;
    }

    public Customer getCustomerNotNull() {
        return this.getCustomer()
            .map((x) -> x)
            .orElseThrow();
        
    }

    public Server getServerNotNull() {
        return this.getServer()
            .map((x) -> x)
            .orElseThrow();
    }

    public String getEventStatus() {
        return this.eventStatus;
    }

    public double getTime() {
        return this.time;
    }

    public boolean isServedEvent() {
        return this.eventStatus.equals("SERVE");
    }

    public boolean isLeaveEvent() {
        return this.eventStatus.equals("LEAVE");
    }

    public boolean isServerRestEvent() {
        return this.eventStatus.equals("SERVER REST");
    }


    public boolean isServerEvent() {
        return this.eventStatus.equals("SERVER REST") || 
            this.eventStatus.equals("SERVER BACK");
    }

    public Event serve(Server server) {
        return new Event(this.getCustomerNotNull(), server,
            "SERVE", this.getTime());
    }

    public Event wait(Server server) {
        return new Event(this.getCustomerNotNull(), server,
            "WAIT", this.getTime());
    }

    public Event leave() {
        return new Event(Optional.<Customer>of(this.getCustomerNotNull()), 
            Optional.empty(), "LEAVE", this.getTime());
    }

    public Event done() {
        // !Customer customer =  line is optional/not needed? need further testing
        Customer customer = this.getServerNotNull().serve(this.getCustomerNotNull());
        return new Event(Optional.<Customer>of(customer), 
            this.getServer(), "DONE", this.getTime());
    }

    public Event goToRest() { //server Rests

        return new Event(Optional.<Customer>of(this.getCustomerNotNull()), 
            this.getServer(), "SERVER REST", this.getTime());
    }

    public Event backFromRest() {

        return new Event(Optional.<Customer>of(this.getCustomerNotNull()), 
            this.getServer(),"SERVER BACK", this.getServerNotNull().getCanServeAt());
    }

    public Optional<Event> mutate(List<Server> serverList) {
        switch(this.getEventStatus()) {
            case ("ARRIVE"):
                return processArriveEvent(serverList);
            case ("SERVE"):
                return processServeEvent();
            case ("WAIT"):
                return processWaitEvent();
            case ("DONE"):
                return processDoneEvent();
            case ("SERVER REST"):
            case ("SERVER BACK"):
                return processServerEvent();
            default:
                return processLeaveEvent();
        }
    }

    protected Optional<Event> processDone() {
        Optional<Customer> newServedCustomer = this.getServerNotNull().done();

        try {
            Customer customer = newServedCustomer
                .map((x) -> x)
                .orElseThrow();
        } catch (NoSuchElementException e) {
            if (this.getServerNotNull().isResting()) {
                return Optional.<Event>of(this.goToRest());
            } else {
                //! remove the else statement to return Optional.empty()? need further testing
                return Optional.empty();
            }
        }

        try {
            Customer newCustomer = newServedCustomer
                .map((x) -> x)
                .orElseThrow();
            double newTime = newCustomer.getTime();
            Event serveEvent = new Event(newCustomer, this.getServerNotNull(), 
                "SERVE", newTime);

            return Optional.<Event>of(serveEvent);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    protected void proccessWait() {
        // get a new server that has an added customer to the queue
        this.getServerNotNull().addToQueue(this.getCustomerNotNull());
    }

    public Optional<Event> processArriveEvent(List<Server> serverList) {
        boolean serveCondition = false;
        Optional<Event> outputEvent = Optional.empty();
        System.out.println(this.toString());

        outputEvent = serverList
            .stream()
            .filter((server) -> server.isIdle())
            .map((server) -> {
                Event servedEvent = new Event(this.getCustomerNotNull(),
                    server, "SERVE", this.getTime());
                return servedEvent;
            })
            .findFirst();

        serveCondition = serverList
            .stream()
            .anyMatch((server) -> server.isIdle());

        if (serveCondition == false && this.getCustomerNotNull().isGreedy()) {
            Optional<Server> mostFreeServer = Optional.empty();
            PriorityQueue<Server> minimumServer = new PriorityQueue<>(
                (server1, server2) -> server1.getQueueLength() - server2.getQueueLength()
            );

            serverList
                .stream()
                .filter((server) -> !server.isFull())
                .forEach((x) -> minimumServer.add(x));
            if (minimumServer.size() > 0) {
                mostFreeServer = Optional.<Server>of(minimumServer.peek());
            }

            try {
                Server server = mostFreeServer
                    .map((x) -> x)
                    .orElseThrow();
                Event waitEvent = new Event(this.getCustomerNotNull(),
                    server, "WAIT", this.getTime());
                outputEvent = Optional.<Event>of(waitEvent);
                serveCondition = true;
            } catch (NoSuchElementException e) {
                serveCondition = false;
            }
            
        } else if (serveCondition == false) {
            outputEvent = serverList
                .stream()
                .filter((server) -> !server.isFull()) // wait event if the server has queue space
                .map((server) -> {
                    Event waitEvent = new Event(this.getCustomerNotNull(),
                        server, "WAIT", this.getTime());
                    return waitEvent;
                })
                .findFirst();

            serveCondition = serverList
                .stream()
                .anyMatch((server) -> !server.isFull());
        }

        if (!serveCondition) {
            Optional<Customer> customer = 
                Optional.<Customer>of(this.getCustomerNotNull());
            Event leftEvent = new Event(customer, this.getServer(), 
                "LEAVE", this.getTime());
            
            outputEvent = Optional.<Event>of(leftEvent);
        }
        return outputEvent;
    }

    public Optional<Event> processServeEvent() {
        System.out.println(this.toString());
        Customer customerDone = this.getServerNotNull()
            .serve(this.getCustomerNotNull());
    

        Event doneEvent = new Event(customerDone, this.getServerNotNull(),
            "DONE", customerDone.getTime());
        
        return Optional.<Event>of(doneEvent);
    }

    public Optional<Event> processLeaveEvent() {
        System.out.println(this.toString());

        return Optional.empty();
    }

    public Optional<Event> processWaitEvent() {
        System.out.println(this.toString());

        // instead of adding to EventQueue, you change this Event's Server customer queue
        
        this.proccessWait();
        
        return Optional.empty();
    }

    public Optional<Event> processDoneEvent() {
        System.out.println(this.toString());

        Optional<Event> newEvent = this.processDone();

        try {
            Event answer = newEvent
                .map((x) -> x)
                .orElseThrow();
            return Optional.<Event>of(answer);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public Optional<Event> processServerBack() {
        Optional<Customer> newlyServedCustomer = this.getServerNotNull().comeBackFromRest();

        try {
            Customer customer = newlyServedCustomer
                .map((x) -> x)
                .orElseThrow();
            Event serveEvent = new Event(customer, this.getServerNotNull(),
                "SERVE", customer.getTime());
            return Optional.<Event>of(serveEvent);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public Optional<Event> processServerEvent() {
        if (this.isServerRestEvent()) {
            Event serverBack = this.backFromRest();

            return Optional.<Event>of(serverBack);
        } else {
            Optional<Event> newEvent = this.processServerBack();

            try {
                return Optional.<Event>of(newEvent
                    .map((x) -> x)
                    .orElseThrow());
            } catch (NoSuchElementException e) {
                return Optional.empty();
            }
        }
    }

    @Override
    public String toString() {
        switch (this.getEventStatus()) {
            case("DONE"):
                return String.format("%.3f %s done serving by %s", time, 
                    getCustomerNotNull(), getServerNotNull());
            case("LEAVE"):
                return String.format("%.3f %s leaves", time, getCustomerNotNull());
            case("WAIT"):
                return String.format("%.3f %s waits at %s", time, 
                    getCustomerNotNull(), getServerNotNull());
            case("ARRIVE"):
                return String.format("%.3f %s arrives", time, getCustomerNotNull());
            case("SERVE"):
                return String.format("%.3f %s serves by %s", time, 
                    getCustomerNotNull(), getServerNotNull());
            default:
                return "";
        } 
    }
}