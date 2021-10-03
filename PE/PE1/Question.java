public abstract class Question {
    
    private final String question;
    private final int answer;
    private final boolean isAnswer;

    public Question(String question, int answer) {
        this.question = question;
        this.answer = answer;
        this.isAnswer = false;
    }

    public Question(String question, int answer, boolean answerTrue) {
        this.question = question;
        this.answer = answer;
        this.isAnswer = answerTrue;
    }

    @Override
    public String toString() {
        if (this.isAnswer) {
             return String.format("%s; Your answer: %d", this.question, this.answer);  
        } else {
             return String.format("%s; Your answer: %d", this.question, 0);
        }
    }

    protected final String getQuestion() {
        return this.question;
    }

    protected final int getAnswer() {
        return this.answer;
    }

    public abstract Question answer(int option);

}
