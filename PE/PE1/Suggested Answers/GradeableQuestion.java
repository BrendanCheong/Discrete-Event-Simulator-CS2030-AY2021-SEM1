abstract class GradeableQuestion extends Question implements Gradeable {
    GradeableQuestion(String description) {
        super(description);
    }

    GradeableQuestion(GradeableQuestion gradeableQuestion) {
        super(gradeableQuestion);
    }

    public abstract int mark();

    @Override
    Gradeable lock() {
        return this;
    }
}

