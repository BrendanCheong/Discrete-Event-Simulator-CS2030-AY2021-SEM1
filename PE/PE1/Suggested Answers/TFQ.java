class TFQ extends MCQ {
    private static final int TRUE_CHOICE = 1;
    private static final int FALSE_CHOICE = 2;
    private static final String[] OPTIONS = {"True", "False"};

    TFQ(String description, String answer) {
        super(description, OPTIONS, convertTextToChoice(answer));
    }

    TFQ(TFQ tfq, int guess) {
        super(tfq, guess);
    }

    @Override
    public TFQ answer(int guess) {
        return new TFQ(this, guess);
    }

    public TFQ answer(String guess) {
        return answer(convertTextToChoice(guess));
    }

    private static int convertTextToChoice(String text) {
        return text.equals("True") ? TRUE_CHOICE : FALSE_CHOICE;
    }
}
