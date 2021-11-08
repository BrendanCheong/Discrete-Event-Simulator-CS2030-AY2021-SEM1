class FillInBlank extends GradeableQuestion {
    private static final int DEFAULT_GUESS = 0;

    private final int guess;
    private final SingleAnswerGrader singleAnswerGrader;

    FillInBlank(String description, int answer) {
        super(description);
        this.guess = DEFAULT_GUESS;
        this.singleAnswerGrader = new ObjectiveSingleAnswerGrader(answer);
    }

    FillInBlank(String description, SingleAnswerGrader singleAnswerGrader) {
        super(description);
        this.guess = DEFAULT_GUESS;
        this.singleAnswerGrader = singleAnswerGrader;
    }

    private FillInBlank(FillInBlank fillInBlank, int guess) {
        super(fillInBlank);
        this.guess = guess;
        this.singleAnswerGrader = fillInBlank.singleAnswerGrader;
    }

    @Override
    public int mark() {
        return singleAnswerGrader.grade(guess); 
    }

    @Override
    public FillInBlank answer(int guess) {
        return new FillInBlank(this, guess);
    }

    @Override
    public String toString() {
        return String.format("%s; Your answer: %d", super.toString(), guess);
    }
}
