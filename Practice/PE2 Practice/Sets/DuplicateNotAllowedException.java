public class DuplicateNotAllowedException extends Exception {

    public DuplicateNotAllowedException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
