class ObjectiveMultipleAnswersGrader implements MultipleAnswersGrader {
    private static final int WRONG_ANSWER_MARKS = 0;
    private static final int ALL_CORRECT_ANSWERS_MARKS = 1;

    private final boolean[] answers;

    ObjectiveMultipleAnswersGrader(int[] answers, int numberOfOptions) {
        this.answers = new boolean[numberOfOptions];

        for (int answer : answers) {
            this.answers[answer - 1] = true;
        }
    }

    @Override
    public int grade(boolean[] guesses) {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] != guesses[i]) {
                return WRONG_ANSWER_MARKS;
            }
        }

        return ALL_CORRECT_ANSWERS_MARKS;
    }
}
