public class TakeACab extends RideService {

    private static final int BOOKING_FEE = 200;
    private static final int SURCHARGE = 0;
    private static final int FARE = 33;

    public TakeACab() {
        super(BOOKING_FEE, SURCHARGE, FARE);
    }

    @Override
    public int computeFare(Request request) {
        return super.getFarePerDistanceExtra(request.getDistance(), BOOKING_FEE);
    }

    @Override
    public String toString() {
        return "TakeACab";
    }

}
