class Loader {

    private final int identifier;
    protected final Cruise cruise;

    public Loader(int identifier, Cruise cruise) {
        this.identifier = identifier;
        this.cruise = cruise;
    }

    /**
     * Checks if the current loader can serve the input cruise.
     * if the loader completes just in time before the next cruise arrives
     * or completes just on time.
     * @param cruise takes in the next cruise to serve
     * @return boolean of whether can serve or not serve
     */
    public boolean canServe(Cruise cruise) {
        // if no cruise being served at the moment, then return true
        if (getNextAvailableTime() <= cruise.getArrivalTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Serves the next cruise by creating a new Loader with a new current cruise.
     * @param cruise to be served
     * @return the new Loader which serves the current cruise or the current loader
     *     if cannot serve the next cruise.
     */
    public Loader serve(Cruise cruise) {
        if (this.canServe(cruise)) {
            return new Loader(getIdentifier(), cruise);
        } else {
            return this;
        }
    }

    protected int getIdentifier() {
        return this.identifier;
    }

    protected int getNextAvailableTime() {
        return this.cruise.getServiceCompletionTime();
    }

    @Override
    public String toString() {
        return "Loader " +
            getIdentifier() +
            " serving " +
            this.cruise.toString();
    }
}
