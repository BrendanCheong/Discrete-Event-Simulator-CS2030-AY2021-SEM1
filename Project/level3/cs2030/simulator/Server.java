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
    private final List<Boolean> restingState;
    private static LinkedList<Double> RESTING_ARRAY; 

    public Server(int id, int queueAmount, LinkedList<Double> restTimeArray) {
        this.id = id;
        this.queue = new LinkedList<>();
        this.currentCustomer = new ArrayList<>(1000);
        this.queueAmount = queueAmount;
        this.canServeAt = new ArrayList<>(1000);
        this.restingState = new ArrayList<>(1000);
        RESTING_ARRAY = restTimeArray;
        currentCustomer.add(Optional.empty());
        canServeAt.add((double) 0);
        restingState.add(false);
    }

    //! remove this if not in use?
    public Server(int id, LinkedList<Customer> queue,
        List<Optional<Customer>> currentCustomer, int queueAmount,
        List<Double> canServeAt, List<Boolean> restingState,
        LinkedList<Double> restTimeArray) {
            this.id = id;
            this.queue = queue;
            this.currentCustomer = currentCustomer;
            this.queueAmount = queueAmount;
            this.canServeAt = canServeAt;
            this.restingState = restingState;
            RESTING_ARRAY = restTimeArray;
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

    public void setServeTime(double servedTime) {
        // remove element from canServeAt
        // ? take note accomodate for first element
        this.canServeAt.add(0, servedTime);
    }

    public boolean isIdle() {
        // idle means that the server is not serving anyone
        // and that no customer is waiting for the server
        // and that the server is not resting
        // the server is truly free!
        Optional<Customer> currentCustomer = this.getCurrentCustomer();
        try {
            Customer customer = currentCustomer
                .map((x) -> x)
                .orElseThrow();
            return false; // if there is a customer(no empty value error) then server is not Idle
        } catch (NoSuchElementException e) {
            return true && this.queue.isEmpty() && !this.isResting();
        }
    }

    public boolean isFull() {
        return getQueueLength() == this.queueAmount;
    }

    public boolean isResting() {
        return this.restingState.get(0);
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
        //!check if the number in rest is not 0 in order to rest AND return Optional.empty()
        double restTime = RESTING_ARRAY.pop();
        if (restTime != 0) {
            this.rest(restTime);
            
            return Optional.empty();
        } 

        if (!this.queue.isEmpty()) {
            return Optional.<Customer>of(
                this.queue
                .pop()
                .setServed()
                .setTime(getCanServeAt()));
        }
        return Optional.empty();
    }

    public void rest(double restTime) {
        this.restingState.add(0, true);

        double currentServeAt = this.getCanServeAt();
        //! rest by popping of the rest time
        this.canServeAt.add(0, currentServeAt + restTime);
    }

    public Optional<Customer> comeBackFromRest() {

        this.restingState.add(0, false);

        if (!this.queue.isEmpty()) {
            return Optional.<Customer>of(
                this.queue
                .pop()
                .setServed()
                .setTime(getCanServeAt()));
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("server %d", getId());
    }
}
