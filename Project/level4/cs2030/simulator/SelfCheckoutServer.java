package cs2030.simulator;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.NoSuchElementException;

public class SelfCheckoutServer extends Server {

    private static final LinkedList<Customer> SELF_CHECKOUT_QUEUE = new LinkedList<>();
    
    public SelfCheckoutServer(int id, int queueAmount, LinkedList<Double> restTimeArray) {
        super(id, queueAmount, restTimeArray);
    }

    @Override
    public LinkedList<Customer> getQueue() {
        return SELF_CHECKOUT_QUEUE;
    }

    @Override
    public int getQueueLength() {
        return SELF_CHECKOUT_QUEUE.size();
    }

    @Override
    public void addToQueue(Customer customer) {
        SELF_CHECKOUT_QUEUE.add(customer);
    }

    @Override
    public boolean isFull() {
        return this.getQueueLength() == this.getQueueAmount();
    }

    @Override
    public boolean isIdle() {
        Optional<Customer> currentCustomer = this.getCurrentCustomer();

        try {
            Customer customer = currentCustomer
                .map((x) -> x)
                .orElseThrow();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    @Override
    public boolean isResting() {
        return false;
    }

    //! remember to put @Override here later for when Server needs the rng
    //! for now always put a LinkedList with 0s only for all Self-Checkout Servers
    public boolean decideToRest() {
        return false;
    }

    @Override
    public Optional<Customer> done() {

        this.getCurrentCustomerList().add(0, Optional.empty());

        if (!this.getQueue().isEmpty()) {
            return Optional.<Customer>of(
                this.getQueue()
                .pop()
                .setServed()
                .setTime(getCanServeAt()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Customer> comeBackFromRest() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("self-check %d", super.getId() + 1);
    }
}
