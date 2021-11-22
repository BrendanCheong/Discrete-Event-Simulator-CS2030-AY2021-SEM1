public abstract class QA {

    String question;
    char answer;

    public QA(String question) {
        this.question = question;
    }

    public abstract void getAnswer();
}
