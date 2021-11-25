import java.util.ArrayList;
import java.util.List;

class ImList<T> {
    private final List<T> list;
    ImList() {
        this.list = new ArrayList<T>();
    }
    private ImList(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }
    ImList<T> add(T elem) {
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.add(elem);
        return newList;
    }
    ImList<T> set(int index, T elem) {
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.set(index, elem);
        return newList;
    }

    ImList<T> update(int index, T elem) {
        return new ImList<T>(this.set(index, elem).add(elem).list);
    }

    // other methods omitted for brevity
}
