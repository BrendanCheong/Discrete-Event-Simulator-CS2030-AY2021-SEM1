package cs2030.simulator;

import java.util.List;

/**
 * Statistics will calculate the average waiting time, total served customers
 * and total customers that left.
 * @author Brendan Cheong
 * @version CS2030 AY 2021-2022 Sem 1
 */
public class Statistics {
    
    private final List<Integer> numberOfServedCustomers;
    private final List<Integer> numberOfLeftCustomers;
    private final List<Double> totalWaitingTime;

    /**
     * Creates a Statistics class that calculates statistics.
     * <p> calculates the average wait time </p>
     * <p> and number of served and customers that left </p>
     * @param numberOfServedCustomers an array of served customers
     * @param numberOfLeftCustomers an array of customers who left
     * @param totalWaitingTime an array of waiting times of customers
     */
    public Statistics(List<Integer> numberOfServedCustomers, 
        List<Integer> numberOfLeftCustomers, List<Double> totalWaitingTime) {
        this.numberOfServedCustomers = numberOfServedCustomers;
        this.numberOfLeftCustomers = numberOfLeftCustomers;
        this.totalWaitingTime = totalWaitingTime;
    }

    private int getSumOfServedCustomers() {
        return this.numberOfServedCustomers.stream()
            .mapToInt((x) -> x)
            .sum();
    }

    private int getSumOfLeftCustomers() {
        return this.numberOfLeftCustomers.stream()
            .mapToInt((x) -> x)
            .sum();
    }

    private double getSumOfWaitingTime() {
        return this.totalWaitingTime.stream()
            .mapToDouble((x) -> x)
            .sum();
    }

    private double getAverageWaitTime() {
        if (getSumOfServedCustomers() == 0) {
            return 0;
        }
    
        return getSumOfWaitingTime() / getSumOfServedCustomers();
    }

    @Override
    public String toString() {
        return String.format("[%.3f %d %d]", getAverageWaitTime(), 
            getSumOfServedCustomers(), getSumOfLeftCustomers());
    }
}
