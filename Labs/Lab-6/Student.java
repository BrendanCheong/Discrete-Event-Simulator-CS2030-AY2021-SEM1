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
}
