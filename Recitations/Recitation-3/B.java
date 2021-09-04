class B extends A {

    B(int x) {
        super(x);
    }

    @Override
    B method() {
        return new B(x);
    }
}
