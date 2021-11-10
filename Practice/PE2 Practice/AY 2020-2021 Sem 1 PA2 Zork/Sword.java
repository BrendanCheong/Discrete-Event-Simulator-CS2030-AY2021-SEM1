import java.util.Map;

public class Sword extends Item {

    private static final String itemName = "Sword";
    private final boolean isPresent;

    public Sword() {
        super(Map.of(
            1, "Sword is shimmering."), 1);
        this.isPresent = false;
    }

    public Sword(Map<Integer, String> states, int index, boolean isPresent) {
        super(states, index);
        this.isPresent = isPresent;
    }

    public boolean isItem(String name) {
        return name.equals(itemName);
    }

    public Item mutate() {
        return new Sword(super.getState(), this.getIndex() + 1,
            this.isPresent);
    }

    public Item equipSword() {
        return new Sword(super.getState(), this.getIndex(), true);
    }

    public Item removeSword() {
        return new Sword(super.getState(), this.getIndex(), false);
    }

    public boolean checkIfPresent() {
        return this.isPresent == true;
    }

    @Override
    public String toString() {
        return "\n" + super.getState().get(1);
    }

}
