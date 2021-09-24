public class PascalCase implements TextFormatter {

    private static final String NAME = "PascalCase";
    private final String input;

    public PascalCase(String input) {
        this.input = input;
    }

    public String format() {
        StringBuilder result = new StringBuilder();
        boolean toConvert = false;
        for (int index = 0; index < this.input.length(); ++index) {
            char letter = this.input.charAt(index);
            if (letter == '_') {
                toConvert = true;
            } else if (toConvert && letter != '_') {
                result.append(Character.toUpperCase(letter));
                toConvert = false;
            } else if (index == 0) {
                result.append(Character.toUpperCase(letter));
            } else {
                result.append(Character.toLowerCase(letter));
            }
        }
        return result.toString();
    }

    public String getName() {
        return NAME;
    }

}
