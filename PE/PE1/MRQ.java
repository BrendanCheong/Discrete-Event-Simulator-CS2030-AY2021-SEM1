public class MRQ implements Question, LockQuestion {

    private final String question;
    private final String[] options;
    private final int[] answers;
    private final int[] selectedOptions;

    /**
     * Creates an MRQ question.
     *
     * @param question the question at hand
     * @param options  the list of options
     * @param answers  the list of answers in order
     */
    public MRQ(String question, String[] options, int[] answers) {
        this(question, options, answers, new int[] { -1, -1, -1, -1 });
    }

    /**
     * Creates an MRQ question with a selected options list.
     *
     * @param question        the question at hand
     * @param options         the list of options
     * @param answers         the list of answers
     * @param selectedOptions the list of selected options by student
     */
    public MRQ(String question, String[] options, int[] answers, int[] selectedOptions) {
        this.question = question;
        this.options = options;
        this.answers = answers;
        this.selectedOptions = selectedOptions;
    }

    /**
     * Answers the MRQ using the selected answer. if duplicate answers, remove like
     * toggle, else add in selected answer into list
     *
     * @return a new MRQ with the selected answer list
     */
    public Question answer(int answer) {
        int[] answerOptions = this.selectedOptions.clone();
        for (int i = 0; i < answerOptions.length; ++i) {
            if (answer == answerOptions[i]) { answerOptions[i] = -1; break;} 
            else if (answerOptions[i] == -1) { answerOptions[i] = answer; break; }
        }
        return new MRQ(this.question, this.options, this.answers, answerOptions);
    }

    public LockQuestion lock() {
        return this;
    }

    private final int[] removeNegativeNumbers(int[] arr) {
        int[] output = new int[arr.length];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) output[k++] = arr[i];
        }
        return output;
    }

    private final int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int tmp = 0;
                if (arr[i] > arr[j]) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    /**
     * Marks the question.
     *
     * @return the desired mark, 1 is correct, 0 is wrong
     */
    public int mark() {
        int positiveSelectedOptions = 0;
        for (int i : this.selectedOptions) {
            if (i >= 0) ++positiveSelectedOptions;
        }
        if (positiveSelectedOptions != this.answers.length) {
            return 0;
        } else {
            int[] copy = removeNegativeNumbers(bubbleSort(selectedOptions));
            for (int i = 0; i < this.answers.length; ++i) {
                if (bubbleSort(this.answers)[i] != copy[i]) return 0;
            }
            return 1;
        }
    }

    @Override
    public String toString() {
        String optionList = "";
        for (int index = 0; index < this.options.length; ++index) {
            optionList += String.format("[%d:%s]", index + 1, this.options[index]);
        }
        optionList += ";";
        String selectedOptions = "";
        for (int i = 0; i < bubbleSort(this.selectedOptions).length; ++i) {
            if (bubbleSort(this.selectedOptions)[i] >= 0) selectedOptions += String.valueOf(this.selectedOptions[i]) + " ";
        }
        if (selectedOptions.length() > 0) return String.format("%s %s Your answer: [ %s]", this.question,optionList, selectedOptions);
        else return String.format("%s %s Your answer: [ ]", this.question, optionList, selectedOptions);
    }
}
