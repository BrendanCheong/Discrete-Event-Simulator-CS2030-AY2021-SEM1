import java.util.ArrayList;

public class CustomList<T> {

    private ArrayList<T> list = new ArrayList<T>();


    public void addElement(T item) {
        list.add(item);
    }

    public void removeElement(T item) {
        list.remove(item);
    }

    public String toString() {
        return list.toString();
    }

    public T get(Integer index) {
        return list.get(index);
    }

}
