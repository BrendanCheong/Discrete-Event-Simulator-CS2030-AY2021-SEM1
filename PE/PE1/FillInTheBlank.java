public class FillInTheBlank extends Question {

    public FillInTheBlank(String question, int answer) {
        super(question, answer);
    }

    public FillInTheBlank(String question, int answer, boolean answerTrue) {
        super(question, answer, answerTrue);
    }

    @Override
    public FillInTheBlank answer(int option) {
        FillInTheBlank theAnswer = new FillInTheBlank(super.getQuestion(),
            option, true);
        return theAnswer;
    }

}
