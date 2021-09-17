abstract class RideService {

    protected final int bookingFee;
    protected final int subcharge;
    protected final int fare;

    public RideService(int bookingFee, int subcharge, int fare) {
        this.bookingFee = bookingFee;
        this.subcharge = subcharge;
        this.fare = fare;
    }

    abstract int computeFare(Request request);
}
