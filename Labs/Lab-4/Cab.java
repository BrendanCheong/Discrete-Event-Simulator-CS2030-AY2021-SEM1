import java.util.PriorityQueue;

public abstract class Cab {

    private final String licensePlate;
    public final int waitingTime;
    private Request request;
    public PriorityQueue<RideService> services = new PriorityQueue<>(100,
        (service1, service2) ->
        service1.computeFare(request) - service2.computeFare(request));

    public Cab(String licensePlate, int waitingTime) {
        this.licensePlate = licensePlate;
        this.waitingTime = waitingTime;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    abstract void addServices();

    @Override
    public String toString() {
        return String.format("%s (%d mins away) ", licensePlate, waitingTime);
    }

}
