class Cruise {

    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoaders;
    private final int serveTime;
    private static final int NUM_OF_MINS_IN_HOUR = 60;
    private static final int HUNDREDTH_PLACE = 100;

    public Cruise(String id, int arrivalTime, int numOfLoaders, int serveTime) {
        this.identifier = id;
        this.arrivalTime = arrivalTime;
        this.numOfLoaders = numOfLoaders;
        this.serveTime = serveTime;
    }

    public int getArrivalTime() {
       int minutes = (this.arrivalTime % HUNDREDTH_PLACE);
       int hours = (this.arrivalTime / HUNDREDTH_PLACE);
       return (hours * NUM_OF_MINS_IN_HOUR) + minutes;
    }
    
    public int getNumOfLoadersRequired() {
        return this.numOfLoaders;
    }

    public int getServiceCompletionTime() {
        return this.getArrivalTime() + this.serveTime;
    }

    @Override
    public String toString() {
        return this.identifier + "@" + String.format("%04d", this.arrivalTime);
    }

}
