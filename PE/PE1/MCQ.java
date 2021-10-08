public class MCQ implements Question, LockQuestion {

    private final String question;
    private final String[] options;
    private final int answer;
    private final int selectedOption;

    /**
     * Creates a new MCQ question.
     * @param question the question at hand
     * @param options the MCQ options to choose from in indexes
     * @param answer the answer to the MCQ in index
     */
    public MCQ(String question, String[] options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.selectedOption = 0;
    }

    /**
     * Creates a new MCQ with a selectedOption.
     * @param question the question at hand
     * @param options the MCQ options to choose from in indexes
     * @param answer the answer to the MCQ in index
     * @param selectedOption the selected option to MCQ in index
     */
    public MCQ(String question, String[] options, int answer, int selectedOption) {
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.selectedOption = selectedOption;
    }

    public Question answer(int option) {
        return new MCQ(this.question, this.options, this.answer, option);
    }

    protected final String getQuestion() {
        return this.question;
    }

    protected final int getAnswer() {
        return this.answer;
    }

    public LockQuestion lock() {
        return this;
    }

    public int mark() {
        return this.selectedOption == this.answer ? 1 : 0;
    }

    @Override
    public String toString() {
        String optionList = "";
        for (int index = 0; index < this.options.length; ++index) {
            optionList += String.format("[%d:%s]", index + 1, this.options[index]);
        }
        optionList += ";";
        if (this.selectedOption == 0) {
            return String.format("%s %s Your answer: [ ? ]", this.question, optionList);
        } else {
            // find the index
            int index = 0;
            for (int i = 0; i < this.options.length; ++i) {
                if (i == this.selectedOption - 1) {
                    index = i;
                    break;
                }
            }
            return String.format("%s %s Your answer: [ %d:%s ]", this.question, optionList, 
                index + 1, this.options[index]);
        }
    }
}