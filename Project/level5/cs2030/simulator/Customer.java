package cs2030.simulator;

public abstract class Customer implements Comparable<Customer> {
    
    private final int customerId;
    private final double arrivalTime;
    private final double time; // serves as a history of sorts
    private final double serveTime; // remains fixed throughout
    private final double greedyProbability;
    private final RandomGenerator random;
    private final boolean useRandomMachine;

    public Customer(int customerId, double arrivalTime, double serveTime,
        double greedyProbability, RandomGenerator random, boolean useRandomMachine) {

        this(customerId, arrivalTime, 
            arrivalTime, serveTime, 
            greedyProbability, random, 
            useRandomMachine);
    }

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
    public boolean equals(Object obj) {
        Customer customer = (Customer) obj;

        return this.customerId == customer.getCustomerId();
    }

    @Override
    public int compareTo(Customer customer) {
        return this.customerId - customer.getCustomerId();
    }

    @Override
    public abstract String toString();
}
