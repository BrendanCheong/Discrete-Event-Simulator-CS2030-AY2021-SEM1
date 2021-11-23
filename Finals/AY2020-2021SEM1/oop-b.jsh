public abstract class O {
    private Object other;

    public Object get() {
        return this.other;
    }

    public void set(Object other) {
        this.other = other;
    }
}

public class A extends O {

}

public class B extends O {

}