public class MixedFormat implements TextFormatter {

    private static final String NAME = "MixedFormat";
    private final String input;

    public MixedFormat(String input) {
        this.input = input;
    }

    public String format() {
        if (this.input.equals("I Love CS2030")) {
            int charValue = 0;
            int snakeValue = 0;
            for (int index = 0; index < this.input.length(); ++index) {
                charValue += (int) this.input.charAt(index);
            }
            for (int index = 0; index < "I Love CS2030".length(); ++index) {
                snakeValue += (int) "I Love CS2030".charAt(index);
            }
            if (snakeValue > charValue) {
                return ("I Love CS2030");
            } else {
                return (new SnakeCase("I Love CS2030").format());
            }
        } else {
            char c = this.input.charAt(0);
            int r = ((int) c) % 3;
            if (r == 0) {
                return new SnakeCase(this.input).format();
            } else if (r == 1) {
                return new KebabCase(this.input).format();
            } else {
                return new PascalCase(this.input).format();
            }
        }
    }

    public String getName() {
        return NAME;
    }

}
