public class TFQ extends MCQ {

    public TFQ(String question, String answer) {
        super(question, new String[] {"True", "False"}, 
            answer.equals("True") ? 1 : 2);
    }

    public TFQ(String question, String answer, int selectedOption) {
        super(question, new String[] {"True", "False"},
            answer.equals("True") ? 1 : 2, selectedOption);
    }

    private final String findAnswer() {
        return super.getAnswer() == 1 ? "True" : "False";
    }

    /**
     * Answers the TFQ question whens theres an inputed String option.
     * @param option the option chosen, either "False" or "True"
     * @return a TFQ question with the inputed option translated into MCQ integer index
     */
    public TFQ answer(String option) {
        return option.equals("True") ? 
            new TFQ(super.getQuestion(), this.findAnswer(), 1) :
            new TFQ(super.getQuestion(), this.findAnswer(), 2);
    }

    @Override
    public Question answer(int option) {
        return new TFQ(super.getQuestion(), this.findAnswer(), option);
    }
}
