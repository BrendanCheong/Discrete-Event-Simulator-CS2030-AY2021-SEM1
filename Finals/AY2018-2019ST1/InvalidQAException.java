public class InvalidQAException extends IllegalArgumentException {

    public InvalidQAException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
