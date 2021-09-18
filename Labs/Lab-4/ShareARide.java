public class ShareARide extends RideService {

    private static final int EARLIEST_TIME = 600;
    private static final int LATEST_TIME = 900;
    private static final int BOOKING_FEE = 0;
    private static final int SURCHARGE = 500;
    private static final int FARE = 50;

    public ShareARide() {
        super(BOOKING_FEE, SURCHARGE, FARE);
    }

    @Override
    public int computeFare(Request request) {
        int distanceFare = request.getDistance() * FARE;

        if (request.getTime() >= EARLIEST_TIME && request.getTime() <= LATEST_TIME) {
            distanceFare += SURCHARGE;
        }
        int individualFare = distanceFare / request.getPax();
        return individualFare;
    }

    @Override
    public String toString() {
        return "ShareARide";
    }

}
