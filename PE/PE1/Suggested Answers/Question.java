abstract class Question {
    private final String description;

    Question(String description) {
        this.description = description;
    }

    Question(Question question) {
        this.description = question.description;
    }

    abstract Question answer(int guess);

    abstract Gradeable lock();

    @Override
    public String toString() {
        return String.format("%s", description);
    }
}
