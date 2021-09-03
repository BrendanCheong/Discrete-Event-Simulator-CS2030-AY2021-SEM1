class BigCruise extends Cruise {

    private static final int SERVE_RATE = 50;
    private static final int LOADER_RATE = 40;
    private static final int CEILING_OFFSET = 1;

    // to get the Math.ceiling without converting into double
    // we use (x + 1 - rate) / rate
    public BigCruise(String id, int arrivalTime, int cruiseLength, int passengers) {
        super(id, arrivalTime,
            (cruiseLength - CEILING_OFFSET + LOADER_RATE) / LOADER_RATE,
            (passengers - CEILING_OFFSET + SERVE_RATE) / SERVE_RATE);
    }

}
