import java.util.List;

public class Server {

    private final int id;
    private final boolean isServingCustomer;
    private final List<Customer> waitingList;
    private final double nextAvailableTime;

    public Server(int id, boolean isServingCustomer,
        List<Customer> waitingList,double nextAvailableTime) {
        this.id = id;
        this.isServingCustomer = isServingCustomer;
        this.waitingList = waitingList;
        this.nextAvailableTime = nextAvailableTime;
    }

    public int getId() {
        return this.id;
    }

    public boolean getIsServingCustomer() {
        return this.isServingCustomer;
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    public boolean canServeCustomerNow() {
        return getIsServingCustomer() == false;
    }

    public boolean hasWaitingCustomer() {
        return this.waitingList.size() > 0;
    }

    public boolean canTakeWaitingCustomer() {
        return (getIsServingCustomer() == true && hasWaitingCustomer() == false);
    }

    @Override
    public String toString() {
        return "server " + this.id;
    }
}
