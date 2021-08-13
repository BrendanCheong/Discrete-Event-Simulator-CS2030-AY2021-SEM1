public class Person {

    private final String name;
    private final String gender;

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public String getGender() {
        return this.gender;
    }

    public String toString() {
        return Person.class.getSimpleName() + "{" +
            "name=" + getName() +
            ", gender=" + getGender() +
            "}";
    }

}
