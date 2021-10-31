package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
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
    private final LinkedList<Double> restTimeArray;
    private static final int RANDOMNESS_LEVEL = 5;
    private final int numberOfSelfCheckout;
    private final RandomGenerator rng;

    // constructor for level 1
    // default queueAmount for waiting = 1
    // default serveTimeArray = an Array of 1.000 created in Main 1
    public Simulator(int numberOfServers, List<Double> timeArray, int numberOfCustomers, 
        int levelStatus, List<Double> serveTimeArray, LinkedList<Double> restTimeArray,
        int numberOfSelfCheckout, int seed, double arrivalRate, double serviceRate,
        double restingRate, double restingProb, double greedyProb) {

        this(numberOfServers, timeArray, numberOfCustomers, levelStatus, 
            1, serveTimeArray, restTimeArray, numberOfSelfCheckout,
            seed, arrivalRate, serviceRate, restingRate, 
            restingProb, greedyProb);
    }

    // constructor for level 2 - 5
    public Simulator(int numberOfServers, List<Double> timeArray, int numberOfCustomers, 
        int levelStatus, int queueAmount, List<Double> serveTimeArray, 
        LinkedList<Double> restTimeArray, int numberOfSelfCheckout,
        int seed, double arrivalRate, double serviceRate,
        double restingRate, double restingProb, double greedyProb) {

        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.serverList = new ArrayList<>(100);
        this.numberOfServers = numberOfServers;
        this.timeArray = timeArray;
        this.numberOfCustomers = numberOfCustomers;
        this.levelStatus = levelStatus;
        this.queueAmount = queueAmount;
        this.serveTimeArray = serveTimeArray;
        this.restTimeArray = restTimeArray;
        this.numberOfSelfCheckout = numberOfSelfCheckout;
        this.rng = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);
        createServers(numberOfServers, queueAmount, numberOfSelfCheckout, restingProb, this.rng);
        createArriveEvents(numberOfCustomers, greedyProb);
    }

    public void simulate() {
        // first create all ArrivalEvents
        // then create all servers
        // hopefully the events will be referencing the same list of servers
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

    public void createServers(int numberOfServers, int queueAmount, int selfCheckOut,
        double restingProb, RandomGenerator rng) {
        boolean useRandomMachine = this.levelStatus == RANDOMNESS_LEVEL;
        IntStream
            .range(0, numberOfServers)
            .forEachOrdered((index) -> {
                Server server = new Server(index + 1, queueAmount, this.restTimeArray,
                    restingProb, rng, useRandomMachine);
                this.serverList.add(server);
            });

        IntStream
            .range(0, selfCheckOut)
            .forEachOrdered((index) -> {
                SelfCheckoutServer server = new SelfCheckoutServer(index + numberOfServers,
                    queueAmount, this.restTimeArray, restingProb, rng, useRandomMachine);
                this.serverList.add(server);
            });
    }

    public void createArriveEvents(int numberOfCustomers, double greedyProb) {
        boolean useRandomMachine = this.levelStatus == RANDOMNESS_LEVEL;
        double timeHolder = 0.000;
        List<Double> serveTimes = this.serveTimeArray;
        List<Double> arrivalTimes = this.timeArray;

        for (int index = 0; index < numberOfCustomers; ++index) {
            if (useRandomMachine) {
                timeHolder += index == 0 
                    ? 0.000 
                    : this.rng.genInterArrivalTime();

                double serveTime = 0.000;
                if (canCreateGreedyCustomer(greedyProb)) {
                    Customer customer = new GreedyCustomer(index + 1, timeHolder,
                        serveTime, greedyProb, this.rng, useRandomMachine);
                    Event arrivalEvent = new Event(customer, "ARRIVE");
                    this.eventQueue.add(arrivalEvent);
                } else {
                    Customer customer = new HumanCustomer(index + 1, timeHolder,
                        serveTime, greedyProb, this.rng, useRandomMachine);
                    Event arrivalEvent = new Event(customer, "ARRIVE");
                    this.eventQueue.add(arrivalEvent);
                }

            } else {
                double arrivalTime = arrivalTimes.get(index);
                double serveTime = serveTimes.get(index);
                Customer customer = new HumanCustomer(index + 1, arrivalTime, serveTime, greedyProb,
                    this.rng, useRandomMachine); 
                Event arrivalEvent = new Event(customer, "ARRIVE");
                this.eventQueue.add(arrivalEvent);
            }
        }
    }

    public boolean canCreateGreedyCustomer(double greedyProbability) {
        double probability = this.rng.genCustomerType();
        return probability < greedyProbability;
    }
}
