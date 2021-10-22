package cs2030.simulator;

import java.util.LinkedList;
import java.util.Optional;
import java.util.NoSuchElementException;

public class Server {
    
    private final int id;
    private final LinkedList<Customer> queue;
    private final LinkedList<Optional<Customer>> currentCustomer;
    private final int queueAmount;
    private final LinkedList<Double> canServeAt;

    public Server(int id, int queueAmount) {
        this.id = id;
        this.queue = new LinkedList<>();
        this.currentCustomer = new LinkedList<>();
        this.queueAmount = queueAmount;
        this.canServeAt = new LinkedList<>();
        canServeAt.add((double) 0);
        currentCustomer.add(Optional.empty());
    }

    public int getId() {
        return this.id;
    }

    public int getQueueLength() {
        return this.queue.size();
    }

    public double getNextServeTime() {
        // no popping, we just want to peek without changing state
        double nextTimeToServe = this.canServeAt.peek();
        return nextTimeToServe;
    }

    public void setServeTime(double servedTime) {
        // remove element from canServeAt
        assert this.canServeAt.size() == 1;
        this.canServeAt.pop();
        this.canServeAt.add(servedTime);
    }

    public boolean isIdle() {
        // idle means that the server is not serving anyone
        // and that no customer is waiting for the server
        // the server is truly free!
        Optional<Customer> currentCustomer = this.currentCustomer.peek();
        try {
            Customer customer = currentCustomer.map((x) -> x)
                .orElseThrow();
            return false; // if there is a customer(no empty value error) then server is not Idle
        } catch (NoSuchElementException e) {
            return true && this.queue.isEmpty();
        }
    }

    public boolean isFull() {
        return getQueueLength() == this.queueAmount;
    }

    public Customer serve(Customer customer) {
        Optional<Customer> newCustomer = Optional.<Customer>of(customer);
        assert this.currentCustomer.size() == 1;
        this.currentCustomer.pop();
        this.currentCustomer.add(newCustomer);

        double nextServeTime = customer.getTime() + customer.getServeTime();
        assert this.canServeAt.size() == 1;
        this.canServeAt.pop();
        this.canServeAt.add(nextServeTime);

        return customer.setDone().setTime(nextServeTime);
    }

    public void addToQueue(Customer customer) {
        this.queue.add(customer);
    }

    public Optional<Customer> done() {
        // when done serving the customer, the currentCustomer served is now Optional.empty
        // however, if the queue has a waiting customer, then output that customer in an optional
        // and change the customer details like when the customer will finish being served
        // if not, return an Optional.empty
        assert this.currentCustomer.size() == 1;
        this.currentCustomer.pop();
        this.currentCustomer.add(Optional.empty());

        if (!this.queue.isEmpty()) {
            return Optional.<Customer>of(
                this.queue
                .pop()
                .setServed()
                .setTime(getNextServeTime()));
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("server %d", getId());
    }
}
