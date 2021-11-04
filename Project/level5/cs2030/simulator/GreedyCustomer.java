package cs2030.simulator;

/**
 * Greedy customer finds the server with the smallest queue size and is the most free.
 * @author Brendan Cheong
 * @version CS2030 AY 2021-2022 Sem 1
 */
public class GreedyCustomer extends Customer {
    
    /**
     * Creates a greedy customer.
     * @param customerId customer id
     * @param arrivalTime time customer arrived to restaurant
     * @param serveTime amount of time customer needs to take to be served
     * @param greedyProbability probability of being a greedy customer
     * @param random the random number generator
     * @param useRandomMachine to use rng or not use
     */
    public GreedyCustomer(int customerId, double arrivalTime, double serveTime,
        double greedyProbability, RandomGenerator random, boolean useRandomMachine) {

        super(customerId, arrivalTime, 
            arrivalTime, serveTime, 
            greedyProbability, random, 
            useRandomMachine);
    }

    /**
     * Creaes a greedy customer.
     * <p> now with a record of the last time the customer was served </p>
     * @param customerId customer id
     * @param arrivalTime time customer arrived to restaurant
     * @param time record of the last time the customer was served
     * @param serveTime amount of time customer needs to take to be served
     * @param greedyProbability probability of being a greedy customer
     * @param random the random number generator
     * @param useRandomMachine to use rng or not use
     */
    public GreedyCustomer(int customerId, double arrivalTime, double time, 
        double serveTime, double greedyProbability, 
        RandomGenerator random, boolean useRandomMachine) {

        super(customerId, arrivalTime, 
            time, serveTime, 
            greedyProbability, random, 
            useRandomMachine);
    }

    @Override
    public boolean isGreedy() {
        return true;
    }

    @Override
    public Customer setTime(double newTime) {
        return new GreedyCustomer(getCustomerId(), getArrivalTime(), newTime,
            getServeTime(), getGreedyProbability(),
            this.getRandom(), super.getUseRandomConditon());
    }

    @Override
    public String toString() {
        return String.format("%d(greedy)", getCustomerId());
    }
}
