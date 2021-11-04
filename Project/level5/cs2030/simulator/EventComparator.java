package cs2030.simulator;

import java.util.Comparator;

/**
 * Compares between events to decide which event to mutate next.
 * @author Brendan Cheong
 * @version CS2030 AY 2021-2022 Sem 1
 */
public class EventComparator implements Comparator<Event> {

    private static final double smallestNumber = 1e-9;
    
    @Override
    public int compare(Event event1, Event event2) {
        double event1Time = event1.getTime();
        double event2Time = event2.getTime();
        Customer event1Customer = event1.getCustomerNotNull();
        Customer event2Customer = event2.getCustomerNotNull();

        // check for tie-breaker
        if (Math.abs((event1Time - event2Time) / event1Time) < smallestNumber) {
            if (event2.isServerEvent()) {
                return 1;
            } else if (event1.isServerEvent()) {
                return -1;
            } else {
                return event1Customer.compareTo(event2Customer);
            }
        }

        if (event1Time < event2Time) {
            return -1;
        } else if (event1Time > event2Time) {
            return 1;
        } else {
            return 0;
        }
    }
}
