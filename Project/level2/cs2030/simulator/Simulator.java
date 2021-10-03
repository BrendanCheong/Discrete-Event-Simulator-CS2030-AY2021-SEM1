package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages all the severs and assigns customers to their respective servers.
 */
public class Simulator {
    private final int numServers;
    private final ArrayList<Double> timeArray;

    /**
     * Simulator to create the array of servers and add events.
     * 
     * @param numServers number of servers based on the int
     * @param timeArray  array of the times (double)
     */
    public Simulator(int numServers, ArrayList<Double> timeArray) {
        this.numServers = numServers;
        this.timeArray = timeArray;
    }

    /**
     * Start the simulation and output the events in order. Execute all the events
     * then order it according to the comparator
     */
    public void simulate() {
        // will be adding events to this events priority queue
        PriorityQueue<Event> eventQueue = new PriorityQueue<>(new EventComparator());
        List<Event> totalEvents = new ArrayList<>(1000);
        List<Server> servers = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        // Variables to update statistics

        // array of servers based on the int from the Scanner
        for (int id = 0; id < numServers; ++id) {
            servers.add(new Server(id + 1, true, false, 0.0));
        }

        for (int id = 0; id < timeArray.size(); ++id) {
            customers.add(new Customer(id + 1, timeArray.get(id)));
        }

        for (Customer customer : customers) {
            eventQueue.add(new ArriveEvent(customer, servers, customer.getArrivalTime()));
        }

        // Add each event into eventQueue
        // poll - execute that event - add that event back into the queue
        // still execute done but dont add back in
        // poll each event based on the comparator

        while (!eventQueue.isEmpty()) {
            Event firstEvent = eventQueue.poll();
            System.out.println(firstEvent);

            // e1 is next event
            Event nextEvent = firstEvent.mutate();

            switch (firstEvent.getName()) {
                case ("arrives"):
                    eventQueue.add(nextEvent);
                    totalEvents.add(new ArriveEvent(nextEvent.getCustomer(), 
                        servers, nextEvent.getTime()));
                    break;
                case ("waits"):
                    eventQueue.add(nextEvent);
                    totalEvents.add(new WaitEvent(nextEvent.getCustomer(), 
                        servers, nextEvent.getServer(), nextEvent.getTime()));
                    break;
                case ("serves"):
                    eventQueue.add(nextEvent);
                    totalEvents.add(new ServeEvent(nextEvent.getCustomer(), 
                        servers, nextEvent.getServer(), nextEvent.getTime()));
                    break;
                case ("done"):
                    totalEvents.add(new DoneEvent(nextEvent.getCustomer(), 
                        servers, nextEvent.getServer(), nextEvent.getTime()));
                    break;
                default:
                    totalEvents.add(new LeaveEvent(nextEvent.getCustomer(), 
                        servers, nextEvent.getServer(), nextEvent.getTime()));
            }
        }

        // finally, print the statistics
        Statistics stats = new Statistics(totalEvents);
        System.out.println(String.format("[%.3f %d %d]", stats.calculateAverageWaitingTime(),
                stats.calculateNumberOfServed(), stats.calculateNumberOfLeft()));
    }
}
