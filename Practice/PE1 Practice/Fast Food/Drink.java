public class Drink extends Item {

    private static final String TYPE = "Drink";

    public Drink(String name, int price, int index) {
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
