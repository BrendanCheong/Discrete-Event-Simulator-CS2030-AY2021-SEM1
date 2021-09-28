import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Menu {

    private final List<Item> menu;

    public Menu() {
        this.menu = new ArrayList<>(100);
    }

    public String add(Item item) {
        this.menu.add(item);
        Collections.sort(this.menu, new MenuComparator());
        return item.toString() + " added!";
    }

    public List<Item> getMenu() {
        return this.menu;
    }

    public void readMenu() {
        for (Item item : this.menu) {
            System.out.println(item);
        }
    }

    public String toString() {
        return String.format("Menu has %d items", this.menu.size());
    }
}
