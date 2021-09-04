class A {

    int x;

    A(int x) {
        this.x = x;
    }

    A method() {
        return new A(x);
    }
}
