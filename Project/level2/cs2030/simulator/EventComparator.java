package cs2030.simulator;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class EventComparator implements Comparator<Event> {
    /**
     * Compares 2 Events and decides which is smaller, equal or greater.
     * <p> First check earliest time </p>
     * <p> Then check based on event rankings to keep the comparator extendable</p>
     * @param event1 first event
     * @param event2 second event
     * @return -1 if event1 is on top of event2, or 1 if event2 is on top, or 0 if same
     **/
    public int compare(Event event1, Event event2) {
        Map<String, Integer> rankings = new HashMap<>();
        rankings.put("done", 1);
        rankings.put("serves", 2);
        rankings.put("arrives", 3);
        // Prioritise Done events above all else
        // Make sure that time for Event2 is accounted for
        double event1Time = event1.getTime();
        double event2Time = event2.getTime();
        if (event1Time < event2Time) {
            return -1;
        } else if (event1Time == event2Time) {
            if (event1.getName().equals(event2.getName())) {
                return 0;
            } else {
                Integer event1Rank = rankings.get(event1.getName());
                Integer event2Rank = rankings.get(event2.getName());
                int result = event1Rank.compareTo(event2Rank);
                return result;
            }
        }
        return 1;
    }
}

