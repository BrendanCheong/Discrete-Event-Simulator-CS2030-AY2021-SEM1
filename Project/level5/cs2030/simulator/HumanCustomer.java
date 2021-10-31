package cs2030.simulator;

public class HumanCustomer extends Customer {
    

    /**
     * Creates a human customer.
     * @param customerId customer id
     * @param arrivalTime time customer arrived to restaurant
     * @param serveTime amount of time customer needs to take to be served
     * @param greedyProbability probability of being a greedy customer
     * @param random the random number generator
     * @param useRandomMachine to use rng or not use
     */
    public HumanCustomer(int customerId, double arrivalTime, double serveTime,
        double greedyProbability, RandomGenerator random, boolean useRandomMachine) {

        super(customerId, arrivalTime, 
            arrivalTime, serveTime, 
            greedyProbability, random, 
            useRandomMachine);
    }

    /**
     * Creates a human customer.
     * <p> now with a record of the last time the customer was served </p>
     * @param customerId customer id
     * @param arrivalTime time customer arrived to restaurant
     * @param time record of the last time the customer was served
     * @param serveTime amount of time customer needs to take to be served
     * @param greedyProbability probability of being a greedy customer
     * @param random the random number generator
     * @param useRandomMachine to use rng or not use
     */
    public HumanCustomer(int customerId, double arrivalTime, double time, 
        double serveTime, double greedyProbability, 
        RandomGenerator random, boolean useRandomMachine) {

        super(customerId, arrivalTime, 
            time, serveTime, 
            greedyProbability, random, 
            useRandomMachine);
    }

    @Override
    public Customer setTime(double newTime) {
        return new HumanCustomer(getCustomerId(), getArrivalTime(), newTime,
            getServeTime(), getGreedyProbability(),
            this.getRandom(), super.getUseRandomConditon());
    }

    @Override
    public boolean isGreedy() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("%d", getCustomerId());
    }
}
