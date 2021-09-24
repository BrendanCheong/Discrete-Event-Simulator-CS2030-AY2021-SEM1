public class KebabCase implements TextFormatter {

    private static final String NAME = "KebabCase";
    private final String input;

    public KebabCase(String input) {
        this.input = input;
    }

    public String format() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < this.input.length(); ++index) {
            char letter = this.input.charAt(index);
            if (letter == '_') {
                letter = '-';
            }
            result.append(Character.toLowerCase(letter));
        }
        return result.toString();
    }

    public String getName() {
        return NAME;
    }

}
