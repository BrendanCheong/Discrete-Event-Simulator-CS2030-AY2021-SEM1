public class Request {

    public final int distance;
    public final int pax;
    public final int time;

    public Request(int distance, int pax, int time) {
        this.distance = distance;
        this.pax = pax;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs",
            this.distance, this.pax, this.time);
    }

}
