public interface Compute<T> {
    
    public boolean isRecursive();

    public Compute<T> recurse();

    public T getAnswer();

}
