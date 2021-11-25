public abstract class TrafficLight {

    private final String color;

    protected TrafficLight(String color) {
        this.color = color;
    }

    public abstract TrafficLight toggle();

    protected String getColor() {
        return this.color;
    }

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
        if (super.getColor().equals("red")) {
            return new GreenLight();
        } else if (super.getColor().equals("green")) {
            return new AmberLight();
        } else {
            return new RedLight();
        }
    }
}

public class GreenLight extends RedLight {

    public GreenLight() {
        super("green");
    }

}

public class AmberLight extends RedLight {

    public AmberLight() {
        super("amber");
    }

}

