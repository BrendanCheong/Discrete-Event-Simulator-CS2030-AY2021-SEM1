class PrivateCar extends Driver {

    // Store all possible services as an Array
    private final RideService[] services = {
        new JustRide(), new ShareARide()
    };

    public PrivateCar(String licensePlate, int waitingTime) {
        super(licensePlate, waitingTime);
    }

    @Override
    public RideService[] getServices() {
        return this.services;
    }

    @Override
    public String toString() {
        return super.toString() + "PrivateCar";
    }


}
