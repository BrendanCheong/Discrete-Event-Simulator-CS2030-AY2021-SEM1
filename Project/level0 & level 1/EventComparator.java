import java.util.Comparator;

public class EventComparator implements Comparator<Event> {

    public int compare(Event event1, Event event2) {
        double event1Time = event1.getCustomer().getArrivalTime();
        double event2Time = event2.getCustomer().getArrivalTime();

        if (event1Time < event2Time) {
            return -1;
        } else if (event1Time == event2Time) {
            if (event1.getName().equals("done") && event2.getName().equals("serves")) {
                return -1;
            } else if (event1.getName().equals("serves") && event2.getName().equals("done")) {
                return 1;
            }

            if (event1.getName().equals("arrives") && event2.getName().equals("serves")) {
                return 1;
            } else if (event1.getName().equals("serves") && event2.getName().equals("arrives")) {
                return -1;
            }

            return 0;
        }
            return 1;
    }
}
