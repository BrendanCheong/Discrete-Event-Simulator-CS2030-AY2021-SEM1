class SmallCruise extends Cruise {

    private final int TIME_TO_LOAD = 30;
    private final int MAX_LOADER_AMT = 1;

    public SmallCruise(String name, int time) {
        super(name, time, 1 , 30);
    }
}
