class OffByOneGrader implements SingleAnswerGrader {
    private static final int WRONG_ANSWER_MARKS = 0;
    private static final int OFF_BY_ONE_ANSWER_MARKS = 1;
    private static final int CORRECT_ANSWER_MARKS = 2;
    private static final int OFF_BY_ONE_THRESHOLD = 1;

    private final int answer;

    OffByOneGrader(int answer) {
        this.answer = answer;
    }

    @Override
    public int grade(int guess) {
        return answer == guess ? CORRECT_ANSWER_MARKS
                : Math.abs(answer - guess) == OFF_BY_ONE_THRESHOLD ? OFF_BY_ONE_ANSWER_MARKS
                : WRONG_ANSWER_MARKS;
    }
}
