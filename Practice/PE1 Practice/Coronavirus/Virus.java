public abstract class Virus {

    public static final String TARGET_VIRUS = "SARS-CoV-2";
    public static final long RANDOM_SEED = 2030;
    public static final double TRACING_PERIOD = 14;
    public static final double SHN_DURATION = 14;
    public static final double VIRUS_MUTATION_PROBABILITY_REDUCTION = 0.9;
    private final String name;
    private final double probabilityOfMutating;

    public Virus(String name, double probabilityOfMutating) {
        this.name = name;
        this.probabilityOfMutating = probabilityOfMutating;
    }

    protected final String getName() {
        return this.name;
    }

    protected final double getProbMutate() {
        return this.probabilityOfMutating;
    }

    public abstract Virus spread(double random);

}
