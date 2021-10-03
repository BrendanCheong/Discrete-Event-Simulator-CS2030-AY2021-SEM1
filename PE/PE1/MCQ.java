public class MCQ {

    private final String question;
    private final String[] options;
    private final int guess;
    private final boolean isAnswer;

    public MCQ(String question, String[] options, int guess) {
        this.question = question;
        this.options = options;
        this.guess = guess;
        this.isAnswer = false;
    }

    public MCQ(String question, String[] options, int guess, boolean answerTrue) {
        this.question = question;
        this.options = options;
        this.guess = guess;
        this.isAnswer = answerTrue;
    }

    public MCQ answer(int answer) {
        return new MCQ(this.question, this.options, answer, true);
    }

    protected final String getQuestion() {
        return this.question;
    }

    public boolean getIsAnswer() {
        return this.isAnswer;
    }


    @Override
    public String toString() {
        String optionList = "";
        for (int index = 0; index < this.options.length; ++index) {
            optionList += String.format("[%d:%s]", index, this.options[index]);
        }
        optionList += ";";
        if (this.isAnswer == false) {
            return String.format("%s %s Your answer: [ ? ]", this.question, optionList);
        } else {
             // find the index
           
             return String.format("%s %s Your answer: [ %d ]", this.question, optionList, this.guess);
        }
    }

}
