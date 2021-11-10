import java.util.HashMap;
import java.util.Map;

public class Candle extends Item {

    private static final String itemName = "Candle";

    public Candle() {
        super(Map.of(
            1, "Candle flickers.",
            2, "Candle is getting shorter.",
            3, "Candle is about to burn out.",
            4, "Candle has burned out."), 1);
    }

    public Candle(Map<Integer, String> states, int index) {
        super(states, index);
    }

    public Item mutate() {
        return new Candle(super.getState(), super.getIndex() + 1);
    }

    public boolean isItem(String name) {
        return name.equals(itemName);
    }

    @Override
    public String toString() {
        int currIndex = this.getIndex();
        return currIndex <= 4
            ? "\n" + super.getState().get(currIndex)
            : "\n" + super.getState().get(4);
    }

}
