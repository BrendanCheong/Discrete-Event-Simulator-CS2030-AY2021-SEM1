import java.util.Comparator;

public class WaitingTimeComparator implements Comparator<Server> {

    public int compare(Server server1, Server server2) {
        double difference = server1.getNextAvailableTime() -
            server2.getNextAvailableTime();

        if (difference < 0) {
            return -1;
        } else if (difference > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
