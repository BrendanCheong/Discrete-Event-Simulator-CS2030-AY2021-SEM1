public class Booking implements Comparable<Booking> {

    private final Cab driver;
    private final Request request;

    public Booking(Cab driver, Request request) {
        this.driver = driver;
        this.request = request;
        this.driver.setRequest(request);
        this.driver.addServices();
    }

    private RideService bestService() {
        return this.driver.services.peek();
    }

    private int bestCalculatedFare() {
        return this.bestService().computeFare(this.request);
    }

    private int compareTime(int time) {
        if (this.driver.waitingTime < time) {
            return -1;
        } else if (this.driver.waitingTime > time) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(Booking booking) {
        if (this.bestCalculatedFare() == booking.bestCalculatedFare()) {
            return compareTime(booking.driver.waitingTime);
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
