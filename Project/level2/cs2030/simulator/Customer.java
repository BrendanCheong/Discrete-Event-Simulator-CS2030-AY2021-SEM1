package cs2030.simulator;

public class Customer implements Comparable<Customer>{
    
    private final int customerId;
    private final double arrivalTime;
    private final double time; // serves as a history of sorts
    private final double serveTime; // remains fixed throughout

    private enum customerState {
        ARRIVES, SERVED, LEAVES, WAITS, DONE
    }
    private final customerState customerStatus;

    public Customer(int customerId, double arrivalTime, double serveTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.time = arrivalTime;
        this.serveTime = serveTime;
        this.customerStatus = customerState.ARRIVES;
    }

    public Customer(int customerId, double arrivalTime, double time, double serveTime, customerState customerStatus) {
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
            getServeTime(), customerState.SERVED);
    }

    public Customer setLeave() {
        return new Customer(getCustomerId(), getArrivalTime(), getTime(), 
            getServeTime(), customerState.LEAVES);
    }

    public Customer setWait() {
        return new Customer(getCustomerId(), getArrivalTime(), getTime(), 
            getServeTime(), customerState.WAITS);
    }

    public Customer setDone() {
        return new Customer(getCustomerId(), getArrivalTime(), getTime(), 
            getServeTime(), customerState.DONE);
    }

    public boolean isArrived() {
        return this.customerStatus == customerState.ARRIVES;
    }

    public boolean isServed() {
        return this.customerStatus == customerState.SERVED;
    }

    public boolean hasLeft() {
        return this.customerStatus == customerState.LEAVES;
    }

    public boolean isDone() {
        return this.customerStatus == customerState.DONE;
    }

    public boolean isWaiting() {
        return this.customerStatus == customerState.WAITS;
    }

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
