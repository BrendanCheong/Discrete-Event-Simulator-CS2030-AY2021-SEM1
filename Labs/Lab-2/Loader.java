public class Loader {
    private final int id;
    private final Cruise cruise;

    public Loader(int id, Cruise cruise) {
        this.id = id;
        this.cruise = cruise;
    }

    public int getIdentifier() {
        return this.id;
    }

    public int getNextAvailableTime() {
        return this.cruise.getServiceCompletionTime();
    }

    public boolean canServe(Cruise cruise) {
        if (this.cruise == null) {
            return true;
        }
        return this.getNextAvailableTime() <= cruise.getArrivalTime();
    }

    public Loader serve(Cruise cruise) {
        if (this.canServe(cruise)) {
            return new Loader(this.id, cruise);
        } else {
            return new Loader(this.id, this.cruise);
        }
    }

    @Override
    public String toString() {
        return "Loader" + this.id + " serving " + this.cruise.toString();
    }
}
