import java.util.Scanner;
import java.util.List;

public class Logic {

    private final List<Console> consoles;

    public Logic(List<Console> consoles) {
        this.consoles = consoles;
    }

    void start() {
        Scanner sc = new Scanner(System.in);
        String command;
        do {
            System.out.print("Enter a command: ");
            command = sc.next();
            for (Console console : consoles) {
                this.invoke(command, console);
            }
        } while (!command.equals("exit"));
        sc.close();
    }

    void invoke(String command, Console console) {
        // do something based on the command
        console.feedback(command + " executed");
    }
}
