List<Customer> customers = new ArrayList<>();
double now = 0;
for (int i = 0; i < numOfCustomers; i++) {
    Customer customer = new Customer();
    customer.setArrivalTime(now);
    customers.add(customer);
    now += rng.genInterArrivalTime();
}

customers
    .stream()
    .forEach((customer) -> customer.setServiceTime(() -> rng.genServiceTime()));
