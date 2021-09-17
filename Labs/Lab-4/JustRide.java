public class JustRide extends RideService {

    private static final int EARLIEST_TIME = 600;
    private static final int LATEST_TIME = 900;

    public JustRide() {
        super(0, 500, 22);
    }

    @Override
    public int computeFare(Request request) {
        if (request.time >= EARLIEST_TIME && request.time <= LATEST_TIME) {
            return super.subcharge + (super.fare * request.distance);
        } else {
            return super.fare * request.distance;
        }
    }

    @Override
    public String toString(){
        return "JustRide";
    }
}
