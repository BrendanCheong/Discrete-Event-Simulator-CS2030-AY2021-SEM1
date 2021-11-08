abstract class ObjectiveQuestion extends GradeableQuestion {
    private final String[] options;

    ObjectiveQuestion(String description, String[] options) {
        super(description);
        this.options = options.clone();
    }

    ObjectiveQuestion(ObjectiveQuestion objectiveQuestion) {
        super(objectiveQuestion);
        this.options = objectiveQuestion.options.clone();
    }

    String getOption(int optionNumber) {
        return String.format("%d:%s", optionNumber, options[optionNumber - 1]);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString() + " ");

        for (int i = 0; i < options.length; i++) {
            stringBuilder.append(String.format("[%s]", getOption(i + 1)));
        }

        stringBuilder.append(";");

        return stringBuilder.toString();
    }
}
