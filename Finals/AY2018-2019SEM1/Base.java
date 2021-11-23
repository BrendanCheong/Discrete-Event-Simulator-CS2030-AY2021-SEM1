import java.util.function.Supplier;

public class Base<T> implements Compute<T> {

    private final Supplier<T> supper;

    public Base(Supplier<T> supper) {
        this.supper = supper;
    }

    public Compute<T> recurse() throws IllegalCallerException {
        throw new IllegalCallerException("Base Case Should Not Be Called!");
    }

    public T getAnswer() {
        return this.supper.get();
    }
    
    public boolean isRecursive() {
        return false;
    }
}
