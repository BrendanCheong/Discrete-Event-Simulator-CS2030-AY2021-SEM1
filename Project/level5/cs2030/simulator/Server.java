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
    private final double restingProb;
    private final RandomGenerator rng;
    private final boolean useRandomMachine;
    private static LinkedList<Double> RESTING_ARRAY; 

    /**
     * Creates a Normal Server that rests when neccessary.
     * @param id server id
     * @param queueAmount size of server's queue
     * @param restTimeArray server's chronological rest times
     * @param restingProb probability to rest
     * @param rng random number generator
     * @param useRandomMachine whether to use rng or not
     */
    public Server(int id, int queueAmount, LinkedList<Double> restTimeArray,
        double restingProb, RandomGenerator rng, boolean useRandomMachine) {

        this.id = id;
        this.queue = new LinkedList<>();
        this.currentCustomer = new ArrayList<>();
        this.queueAmount = queueAmount;
        this.canServeAt = new ArrayList<>();
        this.restingState = new ArrayList<>();
        this.restingProb = restingProb;
        this.rng = rng;
        this.useRandomMachine = useRandomMachine;
        RESTING_ARRAY = restTimeArray;
        currentCustomer.add(Optional.empty());
        canServeAt.add((double) 0);
        restingState.add(false);
    }

    public int getId() {
        return this.id;
    }

    public LinkedList<Customer> getQueue() {
        return this.queue;
    }

    public Optional<Customer> getCurrentCustomer() {
        return this.currentCustomer.get(0);
    }

    public List<Optional<Customer>> getCurrentCustomerList() {
        return this.currentCustomer;
    }

    public int getQueueAmount() {
        return this.queueAmount;
    }

    public double getCanServeAt() {
        return this.canServeAt.get(0);
    }

    public int getQueueLength() {
        return this.queue.size();
    }

    /**
     * Set the next time to serve customers for this server.
     * @param servedTime next time to serve
     */
    public void setServeTime(double servedTime) {
        this.canServeAt.add(0, servedTime);
    }

    /**
     * Based on resting probability and rng, decide whether to rest or not.
     * @return to rest or not to rest
     */
    public boolean decideToRest() {
        return useRandomMachine 
            ? this.restingProb > rng.genRandomRest() 
            : false;
    }

    /**
     * Checks whether the server is idle at the moment.
     * <p> an idle server is a server that has no waiting customer in queue </p>
     * <p> a server that is not resting </p>
     * <p> and a server that is not serving anyone </p>
     * <p> so this server can take a customer </p>
     * @return returns whether the server is idle or not
     */
    public boolean isIdle() {
        Optional<Customer> currentCustomer = this.getCurrentCustomer();

        try {
            Customer customer = currentCustomer
                .map((x) -> x)
                .orElseThrow();
            return false; // if there is a customer(no empty value error) then server is not Idle
        } catch (NoSuchElementException e) {
            return true && this.getQueue().isEmpty() && !this.isResting();
        }
    }

    public boolean isFull() {
        return getQueueLength() == this.queueAmount;
    }

    public boolean isResting() {
        return this.restingState.get(0);
    }

    /**
     * Serves the given customer and updates when the customer finishes being served.
     * @param customer the given customer to serve
     * @return a served customer with the new time where it will finish being served
     */
    public Customer serve(Customer customer) {
        Optional<Customer> newCustomer = Optional.<Customer>of(customer);
    
        this.currentCustomer.add(0, newCustomer);
        double serveTime = useRandomMachine 
            ? rng.genServiceTime()
            : customer.getServeTime();

        double nextServeTime = customer.getTime() + serveTime;

        this.canServeAt.add(0, nextServeTime);

        return customer.setTime(nextServeTime);
    }

    public void addToQueue(Customer customer) {
        this.queue.add(customer);
    }

    /**
     * Completes serving the customer, decide to rest or move on to the next customer.
     *  </p> when done serving the customer, the currentCustomer served is now Optional.empty </p>
     *  </p> however, if the queue has a waiting customer, 
     *   then output that customer in an optional </p>
     *  </p> and change the customer details like when the customer will finish being served </p>
     *  </p> if not, return an Optional.empty </p>
     * @return returns the next customer to serve or no customer
     */
    public Optional<Customer> done() {

        this.currentCustomer.add(0, Optional.empty());
        
        double restTime = useRandomMachine
            ? 0.000
            : RESTING_ARRAY.pop();
        
        if (restTime != 0 && !useRandomMachine) {
            this.rest(restTime);
            
            return Optional.empty();
        } else if (useRandomMachine && decideToRest()) {

            this.rest(rng.genRestPeriod());

            return Optional.empty();
        }

        if (!this.getQueue().isEmpty()) {
            return Optional.<Customer>of(
                this.getQueue()
                .pop()
                .setTime(getCanServeAt()));
        }
        return Optional.empty();
    }

    /**
     * The server officiallys rests given the rest time.
     * @param restTime server rest given the stipulated rest time
     */
    public void rest(double restTime) {
        this.restingState.add(0, true);

        double currentServeAt = this.getCanServeAt();
        
        this.canServeAt.add(0, currentServeAt + restTime);
    }

    /**
     * The server returns from their rest.
     * <p> server can either move on to the next customer to serve </p>
     * <p> or the server can state that they have no customers to serve </p>
     * <p> and thus must wait for the next customer in the simulation </p>
     * @return the next customer to serve or no customer
     */
    public Optional<Customer> comeBackFromRest() {

        this.restingState.add(0, false);

        if (!this.getQueue().isEmpty()) {
            return Optional.<Customer>of(
                this.getQueue()
                .pop()
                .setTime(getCanServeAt()));
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("server %d", getId());
    }
}
