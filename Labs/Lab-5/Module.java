import java.util.List;
import java.util.ArrayList;

public class Module extends KeyableMap<Assessment> {

    public Module(String key) {
        super(key);
    }

    @Override
    public Module put(Assessment assessment) {
        super.put(assessment);
        return this;
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
