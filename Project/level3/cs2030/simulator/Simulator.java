package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.PriorityQueue;
import java.util.Optional;
import java.util.NoSuchElementException;

public class Simulator {
    
    private final PriorityQueue<Event> eventQueue;
    private final List<Server> serverList;
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
        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.serverList = new ArrayList<>();
        this.numberOfServers = numberOfServers;
        this.timeArray = timeArray;
        this.numberOfCustomers = numberOfCustomers;
        this.levelStatus = levelStatus;
        this.queueAmount = queueAmount;
        this.serveTimeArray = serveTimeArray;
        createServers(numberOfServers, queueAmount);
        createArriveEvents(numberOfCustomers);
    }

    public void simulate() {
        // first create all ArrivalEvents
        // then create all servers
        // hopefully the events will be referencing the same list of servers
        // !NOTE: my ideas: after every serve event, the server wants to rest, 
        // !so add a rest time to all the servers rest List for every server in  serverList
        // !do it for every serve event, then when the server wants to rest after a done event, it will take the rest number!
        // !which is the first element of the list!
        List<Integer> numberOfServedCustomers = new ArrayList<>();
        List<Integer> numberOfLeftCustomers = new ArrayList<>();
        List<Double> totalWaitingTime = new ArrayList<>();

        while (!this.eventQueue.isEmpty()) {
            Event event = this.eventQueue.poll();

            try {
                Optional<Event> selectedEvent = event.mutate(this.serverList);
                Event eventToAdd = selectedEvent
                    .map((x) -> x)
                    .orElseThrow();

                if (eventToAdd.isServedEvent()) {
                    double newWaitingTime = eventToAdd.getCustomerNotNull().getWaitingTime();

                    numberOfServedCustomers.add(1);
                    totalWaitingTime.add(newWaitingTime);

                } else if (eventToAdd.isLeaveEvent()) {

                    numberOfLeftCustomers.add(1);
                }
                this.eventQueue.add(eventToAdd);
            } catch (NoSuchElementException e) {
                continue;
            }
        }
        Statistics stats = new Statistics(numberOfServedCustomers, 
            numberOfLeftCustomers, totalWaitingTime);
        System.out.println(stats);
    }

    public void createServers(int numberOfServers, int queueAmount) {
        if (this.levelStatus <= 2) { //! take note of level status clause
            // no rest time LinkedList, initiate as empty linkedList on Server class
            // check if rest time is Empty, if so, then rest time is 0 by default
            IntStream
                .range(0, numberOfServers)
                .forEachOrdered((index) -> {
                    Server server = new Server(index + 1, queueAmount);
                    this.serverList.add(server);
                });
        }
    }

    public void createArriveEvents(int numberOfCustomers) {
        List<Double> serveTimes = this.serveTimeArray;
        List<Double> arrivalTimes = this.timeArray;

        IntStream
            .range(0, numberOfCustomers)
            .forEachOrdered((index) -> {
                double arrivalTime = arrivalTimes.get(index);
                double serveTime = serveTimes.get(index);
                // same for level 1 and beyond
                Customer customer = new Customer(index + 1, arrivalTime, serveTime); 
                Event arrivalEvent = new Event(customer, "ARRIVE");
                this.eventQueue.add(arrivalEvent);
            });
    }
}
