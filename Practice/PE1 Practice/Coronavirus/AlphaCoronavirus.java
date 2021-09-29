public class AlphaCoronavirus extends Virus {

    public AlphaCoronavirus(double probability) {
        super("Alpha Coronavirus", probability);
    }

    @Override
    public Virus spread(double random) {
        if (random <= super.getProbMutate()) {
            return new SARS_CoV_2(super.getProbMutate());
        }
        return new AlphaCoronavirus(super.getProbMutate() *
            VIRUS_MUTATION_PROBABILITY_REDUCTION);
    }

    @Override
    public String toString() {
        return String.format("%s with %.3f probability of mutating",
            super.getName(), super.getProbMutate());
    }

}
