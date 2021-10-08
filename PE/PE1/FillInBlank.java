public class FillInBlank implements Question, LockQuestion {

    private final String question;
    private final int answer;
    private final int selectedOption;
    private final Grader grader;

    /**
     * Creates a new FillInBlank Question.
     * 
     * @param question takes in the question
     * @param answer   takes in answer to question
     */
    public FillInBlank(String question, int answer) {
        this.question = question;
        this.answer = answer;
        this.selectedOption = 0;
        this.grader = new DummyGrader();
    }

    /**
     * Creates a new FillInBlank Question with a Grader.
     * 
     * @param question takes in question
     * @param grader   takes in grader with selected answer
     */
    public FillInBlank(String question, Grader grader) {
        this.question = question;
        this.answer = grader.getAnswer();
        this.selectedOption = 0;
        this.grader = grader;
    }

    /**
     * Takes in a new FillInBlank Question with selected option and grader.
     * 
     * @param question       takes in question
     * @param answer         takes in the answer to question
     * @param selectedOption takes in the selected option to question
     * @param grader         takes in the grader grading question
     */
    public FillInBlank(String question, int answer, int selectedOption, Grader grader) {
        this.question = question;
        this.answer = answer;
        this.selectedOption = selectedOption;
        this.grader = grader;
    }

    /**
     * Answers the question with a selected option.
     * 
     * @param option the selected option
     * @return a new FillInBlank question with the selected option
     */
    public Question answer(int option) {
        FillInBlank theAnswer = new FillInBlank(this.question, this.answer, option, this.grader);
        return theAnswer;
    }

    public LockQuestion lock() {
        return this;
    }

    /**
     * Marks the FillInBlank Question and checks if Grader exists.
     * <p>
     * If grader does not exist (where grader is a dummy) then use default selection
     * </p>
     * <p>
     * If it does exists, then use grader's grade method
     * </p>
     * 
     * @return the mark it deserves
     */
    public int mark() {
        if (this.grader.getAnswer() == 2147483647) {
            return this.answer == this.selectedOption ? 1 : 0;
        }
        return this.grader.grade(this.selectedOption);
    }

    @Override
    public String toString() {
        return String.format("%s; Your answer: %d", this.question, this.selectedOption);
    }
}
