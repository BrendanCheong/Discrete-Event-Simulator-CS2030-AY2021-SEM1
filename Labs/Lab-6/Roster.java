import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.NoSuchElementException;

public class Roster extends KeyableMap<Student> {

    public Roster(String key) {
        super(key);
    }

    @Override
    public Roster put(Student s) {
        super.put(s);
        return this;
    }

    /**
     * Gets the Grade of the Roster that is deeply nested.
     * <p>Demostrate use of spread operator for fun!</p>
     * @param inputs is Student, Module, Assessment
     * @return the grade of the selected queries
     */
    public String getGrade(String...inputs) {
        String student = inputs[0];
        String module = inputs[1];
        String assessment = inputs[2];

        try {
            return super.get(student). // returns Optional<Student>
                flatMap((x) -> x.get(module)). // returns Optional<Module>
                flatMap((x) -> x.get(assessment)). // returns Optional<Assessment>
                map((x) -> x.getGrade()). // will auto wrap the String::getGrade into an Optional
                orElseThrow(); // I could use orElse but this solution is funnier
        } catch (NoSuchElementException e) { // catch Exception
            return String.format("No such record: %s %s %s", student, module, assessment);
        }
    }
}
