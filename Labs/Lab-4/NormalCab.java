public class NormalCab extends Cab {

    private final RideService[] services = {
        new JustRide(), new TakeACab()
    };

    public NormalCab(String licensePlate, int waitingTime) {
        super(licensePlate, waitingTime);
    }

    @Override
    public RideService[] getServices() {
        return this.services;
    }

    @Override
    public String toString() {
        return super.toString() + "NormalCab";
    }

}
