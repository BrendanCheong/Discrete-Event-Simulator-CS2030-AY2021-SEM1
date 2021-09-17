public class Booking implements Comparable<Booking> {

    private final Cab driver;
    private final Request request;

    /**
     * Creates a Booking class.
     * @param driver takes in a Cab type, could be taxi, private car etc
     * @param request takes in the type of request to be fufilled
     *      Also gives the Cab type the request and
     *      Also tells the Cab type to instantiate the type of services it can take
     * */
    public Booking(Cab driver, Request request) {
        this.driver = driver;
        this.request = request;
    }

    private RideService bestService() {
        return this.driver.getBestService(this.request, this.driver.getServices());
    }

    private int bestCalculatedFare() {
        return this.bestService().computeFare(this.request);
    }

    @Override
    public int compareTo(Booking booking) {
        if (this.bestCalculatedFare() == booking.bestCalculatedFare()) {
            return this.driver.compareTime(booking.driver.getWaitingTime());
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
