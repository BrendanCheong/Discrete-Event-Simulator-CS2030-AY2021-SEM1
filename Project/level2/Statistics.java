import java.util.List;

public class Statistics {

    private final List<Event> allEvents;

    public Statistics(List<Event> allEvents) {
        this.allEvents = allEvents;
    }

    public double calculateAverageWaitingTime() {
        double totalWaitingTime = 0.0;
        for (Event event : this.allEvents) {
            if (event.getName().equals("waits")) {
                totalWaitingTime += event.getTime() - event.getCustomer().getArrivalTime();

            }
        }
        System.out.println("\n");
        return totalWaitingTime / this.calculateNumberOfServed();
    }

    public int calculateNumberOfServed() {
        int numOfServed = 0;
        for (Event event : this.allEvents) {
            if (event.getName().equals("serves")) {
                numOfServed += 1;
            }
        }
        return numOfServed;
    }

    public int calculateNumberOfLeft() {
        int numOfLeft = 0;
        for (Event event : this.allEvents) {
            if (event.getName().equals("leaves")) {
                numOfLeft += 1;
            }
        }
        return numOfLeft;
    }

}