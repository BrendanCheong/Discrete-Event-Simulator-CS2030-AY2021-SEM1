package cs2030.simulator;

import java.util.Comparator;

class WaitingTimeComparator implements Comparator<Server> {
    /**
     * Finds the next best server based on lowest server id.
     * Compares between 2 servers to find the lowest id
     * @param server1 takes in the first server
     * @param server2 takes in the seconds server
     * @return -1,0,1 where the first server is always on top
     * */
    public int compare(Server server1, Server server2) {
        // compare lowest server id
        double difference = server1.getServerId() - server2.getServerId();

        if (difference < 0) {
            return -1;
        } else if (difference > 0) {
            return 1;
        } else {
            return 0;
        }

    }
}
