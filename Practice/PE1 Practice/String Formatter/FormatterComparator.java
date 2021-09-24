import java.util.Comparator;

public class FormatterComparator implements Comparator<TextFormatter> {

    public int compare(TextFormatter t1, TextFormatter t2) {
        int t1Value = 0;
        int t2Value = 0;
        String formattedT1 = t1.format();
        String formattedT2 = t2.format();
        for (int index = 0; index < formattedT1.length() ; ++index) {
            t1Value += (int) formattedT1.charAt(index);
        }
        for (int index = 0; index < formattedT2.length(); ++index) {
            t2Value += (int) formattedT2.charAt(index);
        }

        if (t1Value < t2Value) {
            return -1;
        } else if (t1Value > t2Value) {
            return 1;
        } else {
            return 0;
        }

    }

}
