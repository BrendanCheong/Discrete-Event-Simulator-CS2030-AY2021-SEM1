public class OffByOneGrader extends Grader {
    
    public OffByOneGrader(int answer) {
        super(answer);
    }

    /**
     * Grades the question based on the input selected option.
     * @param selectedOption the inputed selected option for FillInBlank
     * @return 2 if same as answer, 1 if within the range and 0 if different
     */
    public int grade(int selectedOption) {
        if (selectedOption == super.getAnswer()) {
            return 2;
        } else if (selectedOption - 1 == super.getAnswer()
            || selectedOption + 1 == super.getAnswer()) {
            return 1;
        }
        return 0;
    }
}
