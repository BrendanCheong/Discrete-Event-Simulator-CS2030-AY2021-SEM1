import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Roster extends KeyableMap<Student> {

    public Roster(String key) {
        super(key);
    }

    @Override
    public Roster put(Student s) {
        super.put(s);
        return this;
    }

    public String getGrade(String student, String module, String assessment) {
        if (Objects.isNull(super.get(student)) || 
            Objects.isNull(super.get(student).get(module)) ||
            Objects.isNull(super.get(student).get(module).get(assessment))) {
                return String.format("No such record: %s %s %s",student, module, assessment);
        } else {
            return super.get(student).get(module).get(assessment).getGrade();
        }
    }

    public String getGrade(String... grades) {
        return this.getGrade(grades[0], grades[1], grades[2]);
    }

    @Override
    public String toString() {
        if (super.getMap().isEmpty()) {
            return String.format("%s: {}", this.getKey());
        } else {
            List<String> labs = new ArrayList<>();
            for (String key : super.getMap().keySet()) {
                labs.add(super.getMap().get(key).toString());
            }
            String processedString = labs.toString().replaceAll("\\[|\\]", "");
            return String.format("%s: {%s}", this.getKey(), processedString);
        }
    }
}
