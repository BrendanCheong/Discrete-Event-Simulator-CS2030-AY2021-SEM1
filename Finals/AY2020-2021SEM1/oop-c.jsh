public abstract class O {
    private final O other;

    public O() {
        this.other = null;
    }

    protected O(O other) {
        this.other = other;
    }

    public O get() {
        return this.other;
    }

    public abstract O set(O other);
}

public class A extends O {

    public A() {
        super(null);
    }

    private A(O other) {
        super(other);
    }

    @Override
    public O set(O other) {
        return new A(other);
    }
}

public class B extends O {

    public B() {
        super(null);
    }

    private B(O other) {
        super(other);
    }

    @Override
    public O set(O other) {
        return new B(other);
    }
}