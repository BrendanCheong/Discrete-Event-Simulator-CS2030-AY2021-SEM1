package cs2030.simulator;

import java.util.List;

public class Statistics {
    
    private final List<Integer> numberOfServedCustomers;
    private final List<Integer> numberOfLeftCustomers;
    private final List<Double> totalWaitingTime;

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
