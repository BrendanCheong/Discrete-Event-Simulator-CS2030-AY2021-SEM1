public class Customer {

    private final int customerID;
    private final double arrivalTime;

    public Customer(int customerID, double arrivalTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d ", getArrivalTime(), getCustomerID());
    }

}
