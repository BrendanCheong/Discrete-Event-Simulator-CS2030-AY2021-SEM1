package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;
import java.util.PriorityQueue;
import java.util.Optional;
import java.util.NoSuchElementException;

public class Simulator {
    
    private final PriorityQueue<Event> eventQueue;
    private final List<Server> serverList;
    private final Statistics stats;
    private final int numberOfServers;
    private final List<Double> timeArray; // customer arrival Times
    private final int numberOfCustomers;
    private final int levelStatus;
    private final int queueAmount;
    private final List<Double> serveTimeArray;

    // constructor for level 1
    // default queueAmount for waiting = 1
    // default serveTimeArray = an Array of 1.000 created in Main 1
    public Simulator(int numberOfServers, List<Double> timeArray, int numberOfCustomers, 
        int levelStatus, List<Double> serveTimeArray) {
        this(numberOfServers, timeArray, numberOfCustomers, levelStatus, 
            1, serveTimeArray);
    }

    // constructor for level 2
    public Simulator(int numberOfServers, List<Double> timeArray, int numberOfCustomers, 
        int levelStatus, int queueAmount, List<Double> serveTimeArray) {
        this.eventQueue = new PriorityQueue<>(); // add Comparator later
        this.serverList = new ArrayList<>();
        this.stats = new Statistics();
        this.numberOfServers = numberOfServers;
        this.timeArray = timeArray;
        this.numberOfCustomers = numberOfCustomers;
        this.levelStatus = levelStatus;
        this.queueAmount = queueAmount;
        this.serveTimeArray = serveTimeArray;
    }

    public void simulate() {
        // first create all ArrivalEvents
        // then create all servers
        // hopefully the events will be referencing the same list of servers
        createServers(this.numberOfServers, this.queueAmount);
        createArriveEvents(this.numberOfCustomers);

        while(!this.eventQueue.isEmpty()) {
            Event event = this.eventQueue.poll();
            try {
                Optional<Event> selectedEvent = event.mutate(this.serverList);
                Event eventToAdd = selectedEvent.map((x) -> x)
                    .orElseThrow();
                this.eventQueue.add(eventToAdd);
            } catch (NoSuchElementException e) {
                continue;
            }
        }
        System.out.println(this.stats);
    }

    public void createServers(int numberOfServers, int queueAmount) {
        if (this.levelStatus <= 2) { //! take note of level status clause
            for (int index = 0; index < numberOfServers; ++index) {
                Server server = new Server(index + 1, queueAmount);
                // no rest time LinkedList, initiate as empty linkedList on Server class
                // check if rest time is Empty, if so, then rest time is 0 by default
                this.serverList.add(server);
            }
        }
    }

    public void createArriveEvents(int numberOfCustomers) {
        List<Double> serveTimes = this.serveTimeArray;
        List<Double> arrivalTimes = this.timeArray;

        for (int index = 0; index < numberOfCustomers; ++index) {
            double arrivalTime = arrivalTimes.get(index);
            double serveTime = serveTimes.get(index);

            Customer customer = new Customer(index + 1, arrivalTime, serveTime); // same for level 1 and beyond
            Event ArrivalEvent = new ArrivalEvent(customer, "ARRIVE", this.stats);
            this.eventQueue.add(ArrivalEvent);
        }
    }
}
