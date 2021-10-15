import java.util.List;
import java.util.ArrayList;

public class Student extends KeyableMap<Module> {

    public Student(String key) {
        super(key);
    }

    @Override
    public Student put(Module module) {
        super.put(module);
        return this;
    }

    @Override
    public String toString() {
        if (super.getMap().isEmpty()) {
            return "";
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
