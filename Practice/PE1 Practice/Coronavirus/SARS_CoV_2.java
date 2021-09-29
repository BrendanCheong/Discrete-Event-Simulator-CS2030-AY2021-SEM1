public class SARS_CoV_2 extends Virus {

    public SARS_CoV_2(double probability) {
        super("SARS-CoV-2", probability);
    }

    @Override
    public Virus spread(double random) {
        if (random <= super.getProbMutate()) {
            return new BetaCoronavirus();
        } else {
            return new SARS_CoV_2(this.getProbMutate() * VIRUS_MUTATION_PROBABILITY_REDUCTION);
        }
    }

    @Override
    public String toString() {
        return String.format("%s with %.3f probability of mutating",
            super.getName(), super.getProbMutate());
    }

}
