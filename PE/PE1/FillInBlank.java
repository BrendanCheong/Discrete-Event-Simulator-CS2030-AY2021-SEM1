public class FillInBlank extends Question {

    public FillInBlank(String question, int answer) {
        super(question, answer);
    }

    public FillInBlank(String question, int answer, boolean answerTrue) {
        super(question, answer, answerTrue);
    }

    @Override
    public FillInBlank answer(int option) {
        FillInBlank theAnswer = new FillInBlank(super.getQuestion(),
            option, true);
        return theAnswer;
    }

}
