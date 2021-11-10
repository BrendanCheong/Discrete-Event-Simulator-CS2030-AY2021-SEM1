import java.util.Map;

public abstract class Item {

    private final Map<Integer, String> state;
    private final int index;

    public Item(Map<Integer, String> state, int index) {
        this.state = state;
        this.index = index;
    }

    protected Map<Integer, String> getState() {
        return this.state;
    }

    protected int getIndex() {
        return this.index;
    }

    public abstract boolean isItem(String name);

    public abstract Item mutate();

}
