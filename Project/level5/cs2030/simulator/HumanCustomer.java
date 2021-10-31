package cs2030.simulator;

public class HumanCustomer extends Customer {
    

    public HumanCustomer(int customerId, double arrivalTime, double serveTime,
    double greedyProbability, RandomGenerator random, boolean useRandomMachine) {

        super(customerId, arrivalTime, 
            arrivalTime, serveTime, 
            greedyProbability, random, 
            useRandomMachine);
    }

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
