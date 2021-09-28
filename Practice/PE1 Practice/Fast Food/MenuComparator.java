import java.util.Comparator;

public class MenuComparator implements Comparator<Item> {

    public int compare(Item item1, Item item2) {
        if (item1.isEqual("Burger")) {
            return -1;
        } else if (item1.isEqual("Drink")) {
            return 1;
        } else if (item1.isEqual("Snack") && item2.isEqual("Drink")){
            return -1;
        } else {
            return 0;
        }
    }

}
