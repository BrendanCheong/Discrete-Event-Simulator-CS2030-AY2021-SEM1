import java.util.Scanner;

public class MCQ extends QA {

    public MCQ(String question) {
        super(question);
    }

    @Override
    public void getAnswer() {
        System.out.print(question + " ");
        answer = (new Scanner(System.in)).next().charAt(0);
        if (answer < 'A' || answer > 'E') {
            throw new InvalidMCQException("Invalid MCQ answer");
        }
    }
}
