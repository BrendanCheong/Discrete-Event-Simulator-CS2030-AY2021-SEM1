package cs2030.simulator;

import java.util.LinkedList;
import java.util.Optional;
import java.util.NoSuchElementException;

/**
 * Self Checkout Counters will are just servers that do not rest.
 * @author Brendan Cheong
 * @version CS2030 AY 2021-2022 Sem 1
 */
public class SelfCheckoutServer extends Server {

    private static final LinkedList<Customer> SELF_CHECKOUT_QUEUE = new LinkedList<>();
    
    public SelfCheckoutServer(int id, int queueAmount, LinkedList<Double> restTimeArray,
        double restingProb, RandomGenerator rng, boolean useRandomMachine) {
        super(id, queueAmount, restTimeArray, restingProb, rng, useRandomMachine);
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

    @Override
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
