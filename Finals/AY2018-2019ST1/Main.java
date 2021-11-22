class Main {
    public static void main(String[] args) {
        try {
            QA mcq = new MCQ("What is the answer to this MCQ?");
            QA tfq = new TFQ("What is the answer to this TFQ?");
            mcq.getAnswer();
            tfq.getAnswer();
        } catch (InvalidQAException ex) {
            System.err.println(ex);
        }
    }
}
