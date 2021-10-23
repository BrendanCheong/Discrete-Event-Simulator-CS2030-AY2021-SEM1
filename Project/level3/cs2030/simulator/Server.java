package cs2030.simulator;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.NoSuchElementException;

public class Server {
    
    private final int id;
    private final LinkedList<Customer> queue;
    private final List<Optional<Customer>> currentCustomer;
    private final int queueAmount;
    private final List<Double> canServeAt;

    public Server(int id, int queueAmount) {
        this.id = id;
        this.queue = new LinkedList<>();
        this.currentCustomer = new ArrayList<>();
        this.queueAmount = queueAmount;
        this.canServeAt = new ArrayList<>();
        currentCustomer.add(Optional.empty());
        canServeAt.add((double) 0);
    }

    public Server(int id, LinkedList<Customer> queue,
        List<Optional<Customer>> currentCustomer, int queueAmount,
        List<Double> canServeAt) {
            this.id = id;
            this.queue = queue;
            this.currentCustomer = currentCustomer;
            this.queueAmount = queueAmount;
            this.canServeAt = canServeAt;
        }

    public int getId() {
        return this.id;
    }

    public LinkedList<Customer> getQueue() {
        return this.queue;
    }

    public Optional<Customer> getCurrentCustomer() {
        // ? Take note always take first index of arrayList
        return this.currentCustomer.get(0);
    }

    public int getQueueAmount() {
        return this.queueAmount;
    }

    public double getCanServeAt() {
        // ? Take note always take first index of arrayList
        return this.canServeAt.get(0);
    }

    public int getQueueLength() {
        return this.queue.size();
    }

    public double getNextServeTime() {
        // no popping, we just want to peek without changing state
        double nextTimeToServe = this.canServeAt.get(0);
        return nextTimeToServe;
    }

    public void setServeTime(double servedTime) {
        // remove element from canServeAt
        // ? take note accomodate for first element
        this.canServeAt.add(0, servedTime);
    }

    public boolean isIdle() {
        // idle means that the server is not serving anyone
        // and that no customer is waiting for the server
        // the server is truly free!
        Optional<Customer> currentCustomer = this.getCurrentCustomer();
        try {
            Customer customer = currentCustomer
                .map((x) -> x)
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
    
        this.currentCustomer.add(0, newCustomer);

        double nextServeTime = customer.getTime() + customer.getServeTime();

        this.canServeAt.add(0, nextServeTime);

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

        this.currentCustomer.add(0, Optional.empty());

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
