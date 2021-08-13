import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/* Demostration on Generics in Methods
 * If the input of said method requires an <T> type
 * Then you must put the <T> in the method itself, next to the Type output
 * In this case, type output is List<T>, next to it is <T>
 */
public class ReverseList {

    public static <T> List<T> reverseList(List<T> originalList) {
        // Create a new List from input so as to not mutate original list
        // ArrayList() accepts an List.of as input
        List<T> reversedList = new ArrayList<>(originalList);
        Collections.reverse(reversedList);
        return reversedList;
    }

    public static void main(String[] args) {
        List<String> originalList = new ArrayList<>();
        originalList.add("Hello");
        originalList.add("There");
        originalList.add("Dibbo");

        System.out.println(reverseList(originalList));
        System.out.println(originalList);

        List<Integer> integerList = List.of(1,3,5,7,9,11,13);
        System.out.println(reverseList(integerList));
        System.out.println(integerList);
    }

}
