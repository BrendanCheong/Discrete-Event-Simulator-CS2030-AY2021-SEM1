import java.util.PriorityQueue;

public abstract class Cab {

    private final String licensePlate;
    private final int waitingTime;

    public Cab(String licensePlate, int waitingTime) {
        this.licensePlate = licensePlate;
        this.waitingTime = waitingTime;
    }

    // All Cabs/Drivers Have the Same Priority Queue Logic
    // which is to get the lowest fare among the services + request given
    // Help how do I indent methods with long arguments???

    protected final RideService getBestService(
        Request request, RideService[] services) {

        PriorityQueue<RideService> serviceQueue = new PriorityQueue<>(100,
            (service1, service2) ->
            service1.computeFare(request) - service2.computeFare(request));

        for (RideService service : services) {
            serviceQueue.add(service);
        }

        return serviceQueue.peek();
    }

    protected final int getWaitingTime() {
        return this.waitingTime;
    }

    protected final int compareTime(int time) {
        if (this.waitingTime < time) {
            return -1;
        } else if (this.waitingTime > time) {
            return 1;
        } else {
            return 0;
        }
    }

    abstract RideService[] getServices();

    @Override
    public String toString() {
        return String.format("%s (%d mins away) ", licensePlate, waitingTime);
    }

}
