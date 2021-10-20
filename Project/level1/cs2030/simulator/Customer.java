package cs2030.simulator;

class Customer {
    private int customerId;
    private final double arrivalTime;

    public Customer(int customerId, double arrivalTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d ", getArrivalTime(), getCustomerId());
    }
}
