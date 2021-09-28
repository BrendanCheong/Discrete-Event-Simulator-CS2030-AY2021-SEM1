public abstract class Item {

    private final String name;
    private final int index;
    private final int price;

    public Item(String name, int price, int index) {
        this.name = name;
        this.price = price;
        this.index = index;
    }

    protected final int getIndex() {
        return this.index;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public abstract boolean isEqual(String input);

    @Override
    public String toString() {
        return String.format("%s (%d)", this.name, this.price);
    }

}
