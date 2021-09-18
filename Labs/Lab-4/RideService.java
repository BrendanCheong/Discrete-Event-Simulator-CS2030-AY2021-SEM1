abstract class RideService {

    private final int bookingFee;
    private final int surcharge;
    private final int fare;

    public RideService(int bookingFee, int surcharge, int fare) {
        this.bookingFee = bookingFee;
        this.surcharge = surcharge;
        this.fare = fare;
    }

    /**
     * Calculates the total cost of driving according to distance travelled.
     * @param distance the distance of request to be travelled
     * @return the calculated cost which is fare * distance
     * */
    public int getFarePerDistance(int distance) {
        return this.fare * distance;
    }

    /**
     * Calculates Fare * Distance + Extra costs, whatever that might be.
     * @param distance the distance of request
     * @param extraCharge the extra cost added
     * @return the calculated cost with extra charge considered
     * */
    public int getFarePerDistanceExtra(int distance, int extraCharge) {
        return extraCharge + (this.fare * distance);
    }

    abstract int computeFare(Request request);
}
