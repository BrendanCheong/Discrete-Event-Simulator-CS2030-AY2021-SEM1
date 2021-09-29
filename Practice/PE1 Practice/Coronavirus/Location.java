import java.util.List;
import java.util.ArrayList;

public class Location {

    private final String name;
    private final List<Person> occupants;

    public Location(String name) {
        this.name = name;
        this.occupants = new ArrayList<>();
    }

    public Location(String name, List<Person> occupants) {
        this.name = name;
        this.occupants = occupants;
    }

    public List<Person> getOccupants() {
        return this.occupants;
    }

    public Location accept(Person person) {
        List<Person> originalPersons = new ArrayList<>(this.occupants);
        originalPersons.add(person);
        return new Location(this.name, originalPersons);
    }

    public Location remove(String personName) {
        int index = 0;
        for (int ind = 0; ind < this.getOccupants().size(); ++ind) {
            if (this.getOccupants().get(ind).equals(personName)) {
                index = ind;
                break;
            }
        }
        List<Person> originalPersons = new ArrayList<>(this.occupants);
        originalPersons.remove(index);
        return new Location(this.name, originalPersons);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
