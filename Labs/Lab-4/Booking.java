import java.util.PriorityQueue;

public class Booking implements Comparable<Booking> {

    private final Driver driver;
    private final Request request;

    /**
     * Creates a Booking class.
     * @param driver takes in a Driver type, could be taxi, private car etc
     * @param request takes in the type of request to be fufilled
     *      Also gives the Driver type the request and
     *      Also tells the Driver type to instantiate the type of services it can take
     * */
    public Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
    }

    // Finds the best service given a list of services and a request
    private final RideService bestService() {
        return this.getBestService(this.request, this.driver.getServices());
    }

    // Finds the computed fare of the best service found
    private final int bestCalculatedFare() {
        return this.bestService().computeFare(this.request);
    }

    /**
     * Gets the Best Service based on a input request.
     * @param request takes in the request to be evaluated
     * @param services takes in an array of services to compare and find the
     *     best service
     * */
    private final RideService getBestService(
        Request request, RideService[] services) {

        PriorityQueue<RideService> serviceQueue = new PriorityQueue<>(100,
            (service1, service2) ->
            service1.computeFare(request) - service2.computeFare(request));

        for (RideService service : services) {
            serviceQueue.add(service);
        }

        return serviceQueue.peek();
    }

    /**
     * Compares the time with the current Booking's Driver waiting time.
     * @param time takes in the input time
     * @return -1,1,0 this is for comparator
     * */
    private final int compareTime(int time) {
        if (this.driver.getWaitingTime() < time) {
            return -1;
        } else if (this.driver.getWaitingTime() > time) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(Booking booking) {
        if (this.bestCalculatedFare() == booking.bestCalculatedFare()) {
            return this.compareTime(booking.driver.getWaitingTime());
        } else if (this.bestCalculatedFare() < booking.bestCalculatedFare()) {
            return -1;
        } else if (this.bestCalculatedFare() > booking.bestCalculatedFare()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("$%.2f using ", (double) this.bestCalculatedFare() / 100) +
            this.driver.toString() +
            " (" + this.bestService().toString() + ")";
    }
}
