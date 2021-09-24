public class SnakeCase implements TextFormatter {

    private static final String NAME = "SnakeCase";
    private final String input;

    public SnakeCase(String input) {
        this.input = input;
    }

    public String format() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < this.input.length(); ++index) {
            char letter = this.input.charAt(index);
            result.append(Character.toLowerCase(letter));
        }
        return result.toString();
    }

    public String getName() {
        return NAME;
    }
}
