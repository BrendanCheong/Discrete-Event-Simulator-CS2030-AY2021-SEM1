public class Assessment implements Keyable {

    private final String key;
    private final String grade;

    public Assessment(String key, String grade) {
        this.key = key;
        this.grade = grade;
    }

    public String getGrade() {
        return this.grade;
    }

    public String getKey() {
        return this.key;
    }

    public void test() {
        System.out.println("Test");
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}", this.getKey(), this.getGrade());
    }

}
