public class JustRide extends RideService {

    private static final int EARLIEST_TIME = 600;
    private static final int LATEST_TIME = 900;
    private static final int BOOKING_FEE = 0;
    private static final int SURCHARGE = 500;
    private static final int FARE = 22;

    public JustRide() {
        super(BOOKING_FEE, SURCHARGE, FARE);
    }

    @Override
    public int computeFare(Request request) {
        if (request.getTime() >= EARLIEST_TIME && request.getTime() <= LATEST_TIME) {
            return super.getFarePerDistanceExtra(request.getDistance(), SURCHARGE);
        } else {
            return super.getFarePerDistance(request.getDistance());
        }
    }

    @Override
    public String toString() {
        return "JustRide";
    }
}
