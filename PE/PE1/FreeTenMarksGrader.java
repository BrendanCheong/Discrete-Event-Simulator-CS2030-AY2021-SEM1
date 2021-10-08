public class FreeTenMarksGrader extends Grader {
    
    public FreeTenMarksGrader() {
        super(10);
    }

    public int grade(int selectedOption) {
        return selectedOption > 0 ? 10 : 10;
    }
}
