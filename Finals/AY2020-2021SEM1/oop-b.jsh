public abstract class O {
    private O other;

    public O get() {
        return this.other;
    }

    public void set(O other) {
        this.other = other;
    }
}

public class A extends O {

}

public class B extends O {

}