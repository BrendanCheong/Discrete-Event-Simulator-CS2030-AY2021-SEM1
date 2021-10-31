package cs2030.simulator;

public class GreedyCustomer extends Customer {
    
    public GreedyCustomer(int customerId, double arrivalTime, double serveTime,
    double greedyProbability, RandomGenerator random, boolean useRandomMachine) {

        super(customerId, arrivalTime, 
            arrivalTime, serveTime, 
            greedyProbability, random, 
            useRandomMachine);
    }

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
