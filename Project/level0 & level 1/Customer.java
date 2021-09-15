public class Customer implements Comparable<Customer> {

    private final static int DEFAULT_ID = 1;
    public final int customerID;

    private enum state {
        ARRIVES, SERVED, WAITS, DONE, LEAVE
    }
    private final state customerStatus;

    public Customer(int customerID, state customerStatus) {
        this.customerID = customerID;
        this.customerStatus = customerStatus;
    }

}
