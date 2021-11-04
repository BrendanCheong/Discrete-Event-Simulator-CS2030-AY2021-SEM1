package cs2030.simulator;

/**
 * Customer class will be served by servers, can have human customers or greedy customers.
 * @author Brendan Cheong
 * @version CS2030 AY 2021-2022 Sem 1
 */
public abstract class Customer implements Comparable<Customer> {
    
    private final int customerId;
    private final double arrivalTime;
    private final double time;
    private final double serveTime;
    private final double greedyProbability;
    private final RandomGenerator random;
    private final boolean useRandomMachine;

    /**
     * Creates a customer that needs to be served and takes time to be served.
     * @param customerId customer id
     * @param arrivalTime time customer arrived to restaurant
     * @param serveTime amount of time customer needs to take to be served
     * @param greedyProbability probability of being a greedy customer
     * @param random the random number generator
     * @param useRandomMachine to use rng or not use
     */
    public Customer(int customerId, double arrivalTime, double serveTime,
        double greedyProbability, RandomGenerator random, boolean useRandomMachine) {

        this(customerId, arrivalTime, 
            arrivalTime, serveTime, 
            greedyProbability, random, 
            useRandomMachine);
    }

    /**
     * Creates a customer that needs to be served and takes time to be served.
     * <p> now with a record of the last time the customer was served </p>
     * @param customerId customer id
     * @param arrivalTime time customer arrived to restaurant
     * @param time record of the last time the customer was served
     * @param serveTime amount of time customer needs to take to be served
     * @param greedyProbability probability of being a greedy customer
     * @param random the random number generator
     * @param useRandomMachine to use rng or not use
     */
    public Customer(int customerId, double arrivalTime, double time, 
        double serveTime, double greedyProbability, 
        RandomGenerator random, boolean useRandomMachine) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.time = time;
        this.serveTime = serveTime;
        this.greedyProbability = greedyProbability;
        this.random = random;
        this.useRandomMachine = useRandomMachine;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public double getTime() {
        return this.time;
    }

    protected double getArrivalTime() {
        return this.arrivalTime;
    }

    protected double getServeTime() {
        return this.serveTime;
    }

    protected double getWaitingTime() {
        return this.time - this.arrivalTime;
    }

    public double getGreedyProbability() {
        return this.greedyProbability;
    }

    protected RandomGenerator getRandom() {
        return this.random;
    }

    protected boolean getUseRandomConditon() {
        return this.useRandomMachine;
    }

    public abstract boolean isGreedy();

    public abstract Customer setTime(double newTime);

    @Override
    public int compareTo(Customer customer) {
        return this.customerId - customer.getCustomerId();
    }

    @Override
    public abstract String toString();
}
