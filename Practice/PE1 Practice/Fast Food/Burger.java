public class Burger extends Item {

    private static final String TYPE = "Burger";

    public Burger(String name, int price, int index) {
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
