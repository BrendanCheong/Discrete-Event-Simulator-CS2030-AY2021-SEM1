public class NormalCab extends Cab {

    public NormalCab(String licensePlate, int waitingTime) {
        super(licensePlate, waitingTime);
    }

    @Override
    public void addServices() {
        super.services.add(new JustRide());
        super.services.add(new TakeACab());
    }

    @Override
    public String toString() {
        return super.toString() + "NormalCab";
    }

}
