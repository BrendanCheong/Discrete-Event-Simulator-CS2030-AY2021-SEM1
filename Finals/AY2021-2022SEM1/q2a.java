public abstract class TrafficLight {

    private final String color;

    protected TrafficLight(String color) {
        this.color = color;
    }

    public abstract TrafficLight toggle();

    @Override
    public String toString() {
        return this.color;
    }
}

public class RedLight extends TrafficLight {

    public RedLight() {
        super("red");
    }

    @Override
    public TrafficLight toggle() {
        return new GreenLight();
    }
}

public class GreenLight extends TrafficLight {

    public GreenLight() {
        super("green");
    }

    @Override
    public TrafficLight toggle() {
        return new RedLight();
    }
}


