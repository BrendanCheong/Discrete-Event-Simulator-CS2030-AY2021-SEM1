class MCQ extends ObjectiveQuestion {
    private static final int DEFAULT_GUESS = 0;

    private final int guess;
    private final ObjectiveSingleAnswerGrader objectiveSingleAnswerGrader;

    MCQ(String description, String[] options, int answer) {
        super(description, options);
        this.guess = DEFAULT_GUESS;
        this.objectiveSingleAnswerGrader = new ObjectiveSingleAnswerGrader(answer);
    }

    MCQ(MCQ mcq, int guess) {
        super(mcq);
        this.guess = guess;
        this.objectiveSingleAnswerGrader = mcq.objectiveSingleAnswerGrader;
    }

    @Override
    public MCQ answer(int guess) {
        return new MCQ(this, guess);
    }

    @Override
    public int mark() {
        return objectiveSingleAnswerGrader.grade(guess);
    }

    @Override
    public String toString() {
        return String.format("%s Your answer: [ %s ]", super.toString(),
                guess == DEFAULT_GUESS ? "?" : getOption(guess));
    }
}
