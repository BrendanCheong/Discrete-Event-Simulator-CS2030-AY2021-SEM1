import java.util.ArrayList;

public class TestList {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 10; ++i) {
            list.add(i);
        }
        CustomList myIntegerList = new CustomList(list);
        System.out.println(myIntegerList);
        myIntegerList.addItem(11);
        myIntegerList.addItem(12);
        myIntegerList.addItem(13);
        myIntegerList.removeItem(13);
        System.out.println(myIntegerList);
        System.out.println(myIntegerList.getItem(9));

    }

}
