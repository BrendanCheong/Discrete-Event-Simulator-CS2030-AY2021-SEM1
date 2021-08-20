public class Page {

    private final String content;

    public Page(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "The Page Contains the Following: \n" + this.content;
    }

}
