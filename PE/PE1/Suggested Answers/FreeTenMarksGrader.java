class FreeTenMarksGrader implements SingleAnswerGrader {
    private static final int DEFAULT_MARKS = 10;

    @Override
    public int grade(int guess) {
        return DEFAULT_MARKS;
    }
}
