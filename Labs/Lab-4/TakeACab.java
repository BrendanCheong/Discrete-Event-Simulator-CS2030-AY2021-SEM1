public class TakeACab extends RideService {

    public TakeACab() {
        super(200, 0 , 33);
    }

    @Override
    public int computeFare(Request request) {
        return super.bookingFee + (super.fare * request.distance);
    }

    @Override
    public String toString() {
        return "TakeACab";
    }

}
