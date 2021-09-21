
import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
	/**
	 * Compares 2 Events and decides which is smaller, equal or greater. The first
	 * key is to check for the earliest time. If there is a tie breaker, customerID
	 * is checked instead.
	 * 
	 * @param event1 first event
	 * @param e2     second event
	 * @return -1 if e1 is prioritised over e2, 1 if e2 is prioritised
	 */
	public int compare(Event event1, Event event2) {
		// 3 cases for time:
		// event1 < event2 --> return event1
		// event1 == event2 --> more
		// event1 > event2 --> return event2
		double event1Time = event1.getTime();
		double event2Time = event2.getTime();
		if (event1Time < event2Time) {
			return -1;
		} else if (event1Time == event2Time) {

			if (event1.getName().equals("done") && event2.getName().equals("serves")) {
				return -1;
			} else if (event1.getName().equals("serves") && event2.getName().equals("done")) {
				return 1;
			} else if (event1.getName().equals("arrives") && event2.getName().equals("serves")) {
				return 1;
			} else if (event1.getName().equals("serves") && event2.getName().equals("arrives")) {
				return -1;
			}
			return 0;
		}
		return 1;
	}
}
