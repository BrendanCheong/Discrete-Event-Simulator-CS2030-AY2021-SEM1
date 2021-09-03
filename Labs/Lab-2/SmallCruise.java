class SmallCruise extends Cruise {

    private static final int SERVE_TIME = 30;
    private static final int LOADER_AMOUNT = 1;

    public SmallCruise(String id, int arrivalTime) {
        super(id, arrivalTime, LOADER_AMOUNT, SERVE_TIME);
    }

}
