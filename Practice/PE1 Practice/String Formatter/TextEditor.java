import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TextEditor {

    private final List<TextFormatter> formatter;

    public TextEditor(List<TextFormatter> formatter) {
        this.formatter = formatter;
    }

    public TextEditor addString(String s) {
        List<TextFormatter> answer = new ArrayList<>();
        Map<String, TextFormatter> dict = new HashMap<>();
        dict.put("SnakeCase", new SnakeCase(s));
        dict.put("KebabCase", new KebabCase(s));
        dict.put("PascalCase", new PascalCase(s));
        dict.put("MixedFormat", new MixedFormat(s));

        for (TextFormatter item : this.formatter) {
            String formatterName = item.getName();
            answer.add(dict.get(formatterName));
        }

        return new TextEditor(answer);
    }

    public void printAll() {
        PriorityQueue<TextFormatter> order =
            new PriorityQueue<>(new FormatterComparator());
        for (TextFormatter formatter : this.formatter) {
            order.add(formatter);
        }
        for (TextFormatter formatter : order) {
            System.out.println(formatter.format());
        }
    }

}
