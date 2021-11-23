import java.util.function.Supplier;

public class Recursive<T> implements Compute<T> {
    
    private final Supplier<Compute<T>> supper;
    
    public Recursive(Supplier<Compute<T>> supper) {
        this.supper = supper;
    }

    public Compute<T> recurse() {
        return this.supper.get();
    }

    public T getAnswer() throws IllegalCallerException {
        throw new IllegalCallerException("Recursive call should not be the Answer!");
    }

    public boolean isRecursive() {
        return true;
    }
}
