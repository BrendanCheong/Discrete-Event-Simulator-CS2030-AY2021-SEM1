public class BetaCoronavirus extends Virus {

    public BetaCoronavirus() {
        super("Beta Coronavirus", 0.0);
    }

    @Override
    public Virus spread(double random) {
        return new BetaCoronavirus();
    }

    @Override
    public String toString() {
        return String.format("%s with %.3f probability of mutating",
            super.getName(), super.getProbMutate());
    }

}
