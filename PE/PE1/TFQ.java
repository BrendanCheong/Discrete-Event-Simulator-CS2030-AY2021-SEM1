public class TFQ extends MCQ {

    private final String stringGuess;

    public TFQ(String question, String guess) {
        super(question, new String[] {"True", "False"}, 0);
        this.stringGuess = guess;
    }

    public TFQ(String question, String guess, boolean answerTrue) {
        super(question, new String[] {"True", "False"}, 0, answerTrue);
        this.stringGuess = guess;
    }

    public TFQ answer(String answer) {
        return new TFQ(super.getQuestion(), this.stringGuess, true); 
    }

    
}
