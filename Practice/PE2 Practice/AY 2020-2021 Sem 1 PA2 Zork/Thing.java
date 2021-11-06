import java.util.List;

public abstract class Thing {
    private final List<String> events;
    private final int state;

    public Thing(List<String> events,int state) {
        this.events = events;
        this.state = state;
    }

    public List<String> getEvents() {
        return this.events;
    }

    public int getState() {
        return this.state;
    }

    public abstract Thing tick();

    @Override
    public String toString() {
        return this.events.get(state);
    }
}

