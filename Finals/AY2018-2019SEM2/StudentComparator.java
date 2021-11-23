import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StudentComparator implements Comparator<Student> {

    private final List<Comparator<Student>> compareList;

    public StudentComparator(List<Comparator<Student>> list) {
        this.compareList = list;
    }

    public int compare(Student s1, Student s2) {
        Optional<Integer> answer = this.compareList
            .stream()
            .filter((x) -> x.compare(s1, s2) != 0)
            .map((x) -> x.compare(s1, s2))
            .findFirst();
        boolean emptiness = answer.
            map((x) -> true)
            .orElseGet(() -> false);
        return emptiness
            ? answer.get()
            : 0;
    }
}
