class MRQ extends ObjectiveQuestion {
    private final ObjectiveMultipleAnswersGrader objectiveMultipleAnswersGrader;
    private final boolean[] guesses;

    MRQ(String description, String[] options, int[] answers) {
        super(description, options);
        this.objectiveMultipleAnswersGrader = new ObjectiveMultipleAnswersGrader(answers, options.length);
        this.guesses = new boolean[options.length];
    }

    private MRQ(MRQ mrq, boolean[] guesses) {
        super(mrq);
        this.objectiveMultipleAnswersGrader = mrq.objectiveMultipleAnswersGrader;
        this.guesses = guesses;
    }

    @Override
    public MRQ answer(int guess) {
        boolean[] newGuesses = guesses.clone();
        newGuesses[guess - 1] = !newGuesses[guess - 1];

        return new MRQ(this, newGuesses);
    }

    @Override
    public int mark() {
        return objectiveMultipleAnswersGrader.grade(guesses);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("%s Your answer: [ ", super.toString()));

        for (int i = 0; i < guesses.length; i++) {
            if (guesses[i]) {
                stringBuilder.append(String.format("%d ", i + 1));
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
