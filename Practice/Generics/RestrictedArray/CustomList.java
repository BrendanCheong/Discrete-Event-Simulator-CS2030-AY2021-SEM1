import java.util.ArrayList;

public class CustomList<T extends Number> {

    private ArrayList<T> list;

    public CustomList(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> getList() {

        return this.list;
    }

    public void addItem(T item) {

        getList().add(item);
    }

    public void removeItem(T item) {

        getList().remove(item);
    }

    public T getItem(int index) {

        return getList().get(index);
    }
    public String toString() {

        return this.getList().toString();
    }
}
