public abstract class Grader {
    
    private final int answer;

    public Grader(int answer) {
        this.answer = answer;
    }

    public int getAnswer() {
        return this.answer;
    }

    public abstract int grade(int selectedOption);
}
