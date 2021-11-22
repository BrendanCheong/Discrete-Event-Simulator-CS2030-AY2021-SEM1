import java.util.Scanner;

public class TFQ extends QA {

    public TFQ(String question) {
        super(question);
    }

    @Override
    public void getAnswer() {
        System.out.print(question + " ");
        answer = (new Scanner(System.in)).next().charAt(0);
        if (answer != 'T' && answer != 'F') {
            throw new InvalidTFQException("Invalid TFQ answer");
        }
    }
}
