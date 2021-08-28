public class Cruise {

    private final String name;
    private final int time;
    private final int loaderNumber;
    private final int serviceTime;

    public Cruise(String name, int time, int loaderNumber, int serviceTime) {
        this.name = name;
        this.time = time;
        this.loaderNumber = loaderNumber;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        // convert everything into minutes
        int minutes = this.time % 100;
        int hours = (this.time / 100) * 60;
        return minutes + hours;
    }

    public int getServiceCompletionTime() {
        return this.getArrivalTime() + this.serviceTime;
    }

    public int getNumOfLoadersRequired() {
        return this.loaderNumber;
    }

    @Override
    public String toString() {
        String formattedTime = String.format("%.4d", this.time);
        return this.name + "@" + formattedTime;
    }
}
