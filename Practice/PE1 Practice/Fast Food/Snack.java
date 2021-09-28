public class Snack extends Item {

    private static final String TYPE = "Snack";

    public Snack(String name, int price, int index) {
        super(name, price, index);
    }

    @Override
    public boolean isEqual(String input) {
        return TYPE.equals(input);
    }

    @Override
    public String toString() {
        return String.format("#%d %s: ", super.getIndex(), TYPE) + super.toString();
    }

}
