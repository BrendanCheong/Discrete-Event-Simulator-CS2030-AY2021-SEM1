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
}
