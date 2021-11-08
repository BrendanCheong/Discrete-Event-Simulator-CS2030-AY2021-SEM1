class ObjectiveSingleAnswerGrader implements SingleAnswerGrader {
    private static final int WRONG_ANSWER_MARKS = 0;
    private static final int CORRECT_ANSWER_MARKS = 1;
    
    private final int answer;

    ObjectiveSingleAnswerGrader(int answer) {
        this.answer = answer;
    }

    @Override
    public int grade(int guess) {
        return answer == guess ? CORRECT_ANSWER_MARKS : WRONG_ANSWER_MARKS;
    }
}
