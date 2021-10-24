package cs2030.simulator;

public class Customer implements Comparable<Customer> {
    
    private final int customerId;
    private final double arrivalTime;
    private final double time; // serves as a history of sorts
    private final double serveTime; // remains fixed throughout
    private final String customerStatus;

    public Customer(int customerId, double arrivalTime, double serveTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.time = arrivalTime;
        this.serveTime = serveTime;
        this.customerStatus = "ARRIVES";
    }

    public Customer(int customerId, double arrivalTime, double time, 
        double serveTime, String customerStatus) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.time = time;
        this.serveTime = serveTime;
        this.customerStatus = customerStatus;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public double getTime() {
        return this.time;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getServeTime() {
        return this.serveTime;
    }

    public double getWaitingTime() {
        return this.time - this.arrivalTime;
    }


    public Customer setTime(double newTime) {
        return new Customer(getCustomerId(), getArrivalTime(), newTime,
            getServeTime(), this.customerStatus);
    }

    public Customer setServed() {
        return new Customer(getCustomerId(), getArrivalTime(), getTime(), 
            getServeTime(), "SERVED");
    }

    public Customer setLeave() {
        return new Customer(getCustomerId(), getArrivalTime(), getTime(), 
            getServeTime(), "LEAVES");
    }

    public Customer setWait() {
        return new Customer(getCustomerId(), getArrivalTime(), getTime(), 
            getServeTime(), "WAITS");
    }

    public Customer setDone() {
        return new Customer(getCustomerId(), getArrivalTime(), getTime(), 
            getServeTime(), "DONE");
    }

    public Customer setDummy() {
        return new Customer(getCustomerId(), getArrivalTime(), getTime(), 
            getServeTime(), "Dummy");
    }

    //! I may not actually need these commented lines of code
    // public boolean isArrived() {
    //     return this.customerStatus.equals("ARRIVES");
    // }

    // public boolean isServed() {
    //     return this.customerStatus.equals("SERVED");
    // }

    // public boolean hasLeft() {
    //     return this.customerStatus.equals("LEAVES");
    // }

    // public boolean isDone() {
    //     return this.customerStatus.equals("DONE");
    // }

    // public boolean isWaiting() {
    //     return this.customerStatus.equals("WAITS");
    // }

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
    public String toString() {
        return String.format("%d", getCustomerId());
    }
}
