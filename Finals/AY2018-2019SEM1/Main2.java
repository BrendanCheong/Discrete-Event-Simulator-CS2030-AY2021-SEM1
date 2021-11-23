import java.util.List;
import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) {
        Console primary = new Console("primary");
        Console secondary = new Console("secondary");
        List<Console> list = new ArrayList<>(List.of(primary, secondary));
        Logic logic = new Logic(list);

        logic.start();
    }
}
