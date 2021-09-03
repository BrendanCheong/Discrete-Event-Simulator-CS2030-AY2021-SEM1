class Loader {

    private final int identifier;
    private final Cruise cruise;

    public Loader(int identifier, Cruise cruise) {
        this.identifier = identifier;
        this.cruise = cruise;
    }

    public boolean canServe(Cruise cruise) {
        // if no cruise being served at the moment, then return true
        if (this.cruise.getServiceCompletionTime() <= cruise.getArrivalTime()) {
            return true;
        } else {
            return false;
        }
    }

    public Loader serve(Cruise cruise) {
        if (this.canServe(cruise)) {
            return new Loader(this.identifier, cruise);
        } else {
            return this;
        }
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public int getNextAvailableTime() {
        return this.cruise.getServiceCompletionTime();
    }

    @Override
    public String toString() {
        return "Loader " + this.identifier + " serving " + this.cruise.toString();
    }

}
