public abstract class O {
    private final Object other;

    public O() {
        this.other = null;
    }

    protected O(Object other) {
        this.other = other;
    }

    public Object get() {
        return this.other;
    }

    public abstract O set(Object other);
}

public class A extends O {

    public A(Object other) {
        super(other);
    }

    @Override
    public O set(Object other) {
        return new A(other);
    }
}

public class B extends O {

    public B(Object other) {
        super(other);
    }

    @Override
    public O set(Object other) {
        return new B(other);
    }
}