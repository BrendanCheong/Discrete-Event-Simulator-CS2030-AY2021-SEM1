package cs2030.simulator;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

public abstract class Event implements Comparable<Event>{

    private final Optional<Customer> customer;
    private final Optional<Server> server;
    private final String eventStatus;
    private final double time;
    private final Statistics stats;

    public Event(Customer customer, String eventStatus, Statistics stats) {
        // by default, the event has no server allocated as we do not know what kind of event it is
        this.customer = Optional.<Customer>of(customer);
        this.server = Optional.empty();
        this.eventStatus = eventStatus;
        this.time = customer.getArrivalTime();
        this.stats = stats;
    }

    public Event(Customer customer, Server server, 
        String eventStatus, double time, Statistics stats) {
            this.customer = Optional.<Customer>of(customer);
            this.server = Optional.<Server>of(server);
            this.eventStatus = eventStatus;
            this.time = time;
            this.stats = stats;
    }

    public Event(Optional<Customer> customer, Optional<Server> server,
        String eventStatus, double time, Statistics stats) {
            this.customer = customer;
            this.server = server;
            this.eventStatus = eventStatus;
            this.time = customer.get().getArrivalTime();
            this.stats = stats;
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

    public Statistics getStats() {
        return this.stats;
    }

    public boolean isArriveEvent() {
        return this.eventStatus.equals("ARRIVE");
    }

    public boolean isServedEvent() {
        return this.eventStatus.equals("SERVE");
    }

    public boolean isWaitEvent() {
        return this.eventStatus.equals("WAIT");
    }

    public boolean isDoneEvent() {
        return this.eventStatus.equals("DONE");
    }

    public boolean isLeaveEvent() {
        return this.eventStatus.equals("LEAVE");
    }

    protected Optional<Event> processDone() {
        Optional<Customer> newServedCustomer = this.getServerNotNull().done();

        if (newServedCustomer.isEmpty()) {
            return Optional.empty();
        }

        Event serveEvent = new ServeEvent(newServedCustomer.get(), this.getServerNotNull(), "SERVE",
            newServedCustomer.get().getTime(), this.getStats());
        return Optional.<Event>of(serveEvent);
    }

    protected void proccessWait() {
        this.getServerNotNull().addToQueue(this.getCustomerNotNull());
    }

    protected abstract Optional<Event> mutate(List<Server> serverList);

    @Override
    public int compareTo(Event event) {
        if (Math.abs((time - event.getTime()) / time) < 1e-9) {
            return getCustomerNotNull().compareTo(event.getCustomerNotNull());
        }

        if (time < event.getTime()) {
            return -1;
        } else if (time > event.getTime()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        switch (this.getEventStatus()) {
            case("DONE"):
                return String.format("%.3f %s done serving by %s", time, getCustomerNotNull(), getServerNotNull());
            case("LEAVE"):
                return String.format("%.3f %s leaves", time, getCustomerNotNull());
            case("WAIT"):
                return String.format("%.3f %s waits at %s", time, getCustomerNotNull(), getServerNotNull());
            case("ARRIVE"):
                return String.format("%.3f %s arrives", time, getCustomerNotNull());
            case("SERVE"):
                return String.format("%.3f %s serves by %s", time, getCustomerNotNull(), getServerNotNull());
            default:
                return "";
        } 
    }
}
