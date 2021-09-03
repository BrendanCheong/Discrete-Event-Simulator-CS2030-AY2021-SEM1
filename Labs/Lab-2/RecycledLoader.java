class RecycledLoader extends Loader {

    private static final int MAINTENANCE_TIME = 60;

    public RecycledLoader(int identifier, Cruise cruise) {
        super(identifier, cruise);
    }

    public boolean canServe(Cruise cruise) {
        int recycledLoaderTime = getNextAvailableTime() + MAINTENANCE_TIME;
        if (recycledLoaderTime <= cruise.getArrivalTime()) {
            return true;
        } else {
            return false;
        }
    }

    public RecycledLoader serve(Cruise cruise) {
        if (this.canServe(cruise)) {
            return new RecycledLoader(getIdentifier(), cruise);
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        return "Recycled Loader " +
             getIdentifier() +
            " serving " +
            this.cruise.toString();
    }

}
