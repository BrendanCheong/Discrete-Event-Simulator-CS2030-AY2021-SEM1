import java.util.List;
import java.util.ArrayList;

public class Order {

    private final Menu menu;
    private final int[] orders;

    public Order(Menu menu, int[] orders) {
        this.menu = menu;
        this.orders = orders;
    }

    public int getTotal(List<Item> menu) {
        int total = 0;
        for (Item item : menu) {
            total += item.getPrice();
        }
        return total;
    }

    public void readOrder() {
        System.out.println("--- Order ---");
        List<Item> menu = this.menu.getMenu();
        List<Item> ordersList = new ArrayList<>();
        for (int order : this.orders) {
            System.out.println(menu.get(order));
            ordersList.add(menu.get(order));

        }
        System.out.println(String.format("Total: %d", this.getTotal(ordersList)));
    }

    @Override
    public String toString() {
        return "Order Up!";
    }

}
