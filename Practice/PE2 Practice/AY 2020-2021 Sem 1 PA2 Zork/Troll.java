import java.util.Map;

public class Troll extends Item {

    private static final String itemName = "Troll";

    public Troll() {
        super(Map.of(
            1, "Troll lurks in the shadows.",
            2, "Troll is getting hungry.",
            3, "Troll is getting VERY hungry.",
            4, "Troll is SUPER HUNGRY and is about to ATTACK!",
            5, "Troll attacks!"), 1);
    }

    public Troll(Map<Integer, String> states, int index) {
        super(states, index);
    }

    public Item mutate() {
        return new Troll(super.getState(), super.getIndex() + 1);
    }

    public boolean isItem(String name) {
        return name.equals(itemName);
    }

    @Override
    public String toString() {
        int index = this.getIndex();
        return index <= 5
            ? "\n" + super.getState().get(index)
            : "\n" + super.getState().get(5);
    }

}
