public class Request {

    private final int distance;
    private final int pax;
    private final int time;

    /**
     * Creates a request class.
     * @param distance takes in distance of request for the ride
     * @param pax takes in the number of people in request in the ride
     * @param time takes in time taken to finish request
     * */
    public Request(int distance, int pax, int time) {
        this.distance = distance;
        this.pax = pax;
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    public int getDistance() {
        return this.distance;
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs",
            this.distance, this.pax, this.time);
    }

}
