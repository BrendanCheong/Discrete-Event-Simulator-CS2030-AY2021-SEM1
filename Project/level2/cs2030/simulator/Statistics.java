package cs2030.simulator;

import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;

public class Statistics {
    
    private final List<Integer> numberOfServedCustomers;
    private final List<Integer> numberOfLeftCustomers;
    private final List<Double> totalWaitingTime;

    public Statistics() {
        this.numberOfServedCustomers = new ArrayList<>();
        this.numberOfLeftCustomers = new ArrayList<>();
        this.totalWaitingTime = new ArrayList<>();
    }

    public void addServedCustomersByOne() {
        this.numberOfServedCustomers.add(1);
    }

    public void addLeftCustomersByOne() {
        this.numberOfLeftCustomers.add(1);
    }

    public void addTotalWaitingTime(double time) {
        this.totalWaitingTime.add(time);
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
