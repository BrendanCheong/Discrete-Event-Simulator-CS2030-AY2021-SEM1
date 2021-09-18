public abstract class Driver {

    private final String licensePlate;
    private final int waitingTime;

    public Driver(String licensePlate, int waitingTime) {
        this.licensePlate = licensePlate;
        this.waitingTime = waitingTime;
    }

    protected final int getWaitingTime() {
        return this.waitingTime;
    }

    abstract RideService[] getServices();

    @Override
    public String toString() {
        return String.format("%s (%d mins away) ", licensePlate, waitingTime);
    }

}
