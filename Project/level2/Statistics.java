

import java.util.List;

public class Statistics {

    private final List<Event> allEvents;
    /**
     * Creates a Statitics class to calculate all the statistics needed.
     * <p>Easily extendable, just add more methods</p>
     * <p>each Event in the list is a new Event to prevent mutability errors</p>
     * @param allEvents takes in a list of all events from the simulator
     **/

    public Statistics(List<Event> allEvents) {
        this.allEvents = allEvents;
    }

    /**
     * Calculates average waiting time of customers.
     * <p>takes in all wait events and then calculates event duration - customer
     * arrival time </p>
     * @return avergae waiting time
     **/

    public double calculateAverageWaitingTime() {
        double totalWaitingTime = 0.0;
        for (Event event : this.allEvents) {
            if (event.getName().equals("waits")) {
                totalWaitingTime += event.getTime() -
                    event.getCustomer().getArrivalTime();

            }
        }
        return totalWaitingTime / this.calculateNumberOfServed();
    }

    /**
     * Calculates the total number of serve events.
     * @return total serve event amount
     **/

    public int calculateNumberOfServed() {
        int numOfServed = 0;
        for (Event event : this.allEvents) {
            if (event.getName().equals("serves")) {
                numOfServed += 1;
            }
        }
        return numOfServed;
    }

    /**
     * Calculates the total number of customer leaving.
     * <p>does this by calculating the number of leave events</p>
     * @return total leave event amount
     **/

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
