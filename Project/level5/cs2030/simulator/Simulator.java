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

    /**
     * Creates a discrete event simulator, default queue size is 1 if level 1.
     * <p> Written by Brendan Cheong 2021 for Semester 1 AY 2021-2022</p>
     * @param numberOfServers total number of servers in the simulator
     * @param timeArray arrival times of customers 
     * @param numberOfCustomers total number of customers
     * @param levelStatus current level being used
     * @param serveTimeArray time needed to serve customers
     * @param restTimeArray available rest times of servers
     * @param numberOfSelfCheckout total number of self-checkout counters
     * @param seed initial seed for rng
     * @param arrivalRate rate of arrival for rng
     * @param serviceRate rate of service for rng
     * @param restingRate rate of rest for rng
     * @param restingProb probability to rest
     * @param greedyProb probability to be a greedy customer
     */
    public Simulator(int numberOfServers, List<Double> timeArray, int numberOfCustomers, 
        int levelStatus, List<Double> serveTimeArray, LinkedList<Double> restTimeArray,
        int numberOfSelfCheckout, int seed, double arrivalRate, double serviceRate,
        double restingRate, double restingProb, double greedyProb) {

        this(numberOfServers, timeArray, numberOfCustomers, levelStatus, 
            1, serveTimeArray, restTimeArray, numberOfSelfCheckout,
            seed, arrivalRate, serviceRate, restingRate, 
            restingProb, greedyProb);
    }

    /**
     * Creates a discrete event simulator with all parameters.
     * @param numberOfServers total number of servers in the simulator
     * @param timeArray arrival times of customers 
     * @param numberOfCustomers total number of customers
     * @param queueAmount total customer queue size for all servers
     * @param levelStatus current level being used
     * @param serveTimeArray time needed to serve customers
     * @param restTimeArray available rest times of servers
     * @param numberOfSelfCheckout total number of self-checkout counters
     * @param seed initial seed for rng
     * @param arrivalRate rate of arrival for rng
     * @param serviceRate rate of service for rng
     * @param restingRate rate of rest for rng
     * @param restingProb probability to rest
     * @param greedyProb probability to be a greedy customer
     */
    public Simulator(int numberOfServers, List<Double> timeArray, int numberOfCustomers, 
        int levelStatus, int queueAmount, List<Double> serveTimeArray, 
        LinkedList<Double> restTimeArray, int numberOfSelfCheckout,
        int seed, double arrivalRate, double serviceRate,
        double restingRate, double restingProb, double greedyProb) {

        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.serverList = new ArrayList<>();
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

    /**
     * Starts the simulation of the Discrete Event Simulator.
     * <p> polls the event queue until no events are left </p>
     * <p> each event will mutate, aka move on to the next event and be added back
     * into the queue </p>
     * <p> finally, each event statistics is added into the statistics class for 
     * calculation of statistics </p>
     */
    public void simulate() {
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

    /**
     * Creates all normal servers and self checkout servers.
     * @param numberOfServers number of servers to be created
     * @param queueAmount the queue size of each server
     * @param selfCheckOut the number of self-checkout counters
     * @param restingProb probability to rest
     * @param rng the RandomNumber generator
     */
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

    /**
     * Creates all arrival events together with greedy or human customers.
     * @param numberOfCustomers total number of customers to be created
     * @param greedyProb probability to be a greedy customer
     */
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

    /**
     * Checks whether a greedy customer needs to created based on probability.
     * @param greedyProbability probability a greedy customer needs to made
     * @return true -> make a greedy customer, false -> make a human customer
     */
    public boolean canCreateGreedyCustomer(double greedyProbability) {
        double probability = this.rng.genCustomerType();
        return probability < greedyProbability;
    }
}
