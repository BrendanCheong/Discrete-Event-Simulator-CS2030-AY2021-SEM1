class A {
    private final A other;

    public A() {
        this.other = null;
    }

    protected A(A other) {
        this.other = other;
    }

    A set(A other) {
        return new A(other);
    }

    A get() {
        return this.other;
    }
}

class B extends A {

}