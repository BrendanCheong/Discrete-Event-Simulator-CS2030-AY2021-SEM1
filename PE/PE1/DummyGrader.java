public class DummyGrader extends Grader {

    public DummyGrader() {
        super(2147483647);
    }

    public int grade(int selectedOption) {
        return super.getAnswer();
    }
}
