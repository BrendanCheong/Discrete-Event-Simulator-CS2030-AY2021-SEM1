import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * A database of {@link Person people}
 */
class PersonDatabase {

    private final List<Person> listOfPersons = new ArrayList<>();

    /**
     * Gets a person given his/her name
     * @param personName The name of the person
     * @return the resulting person
     */
    Person getPerson(String personName) {
        for (Person p : listOfPersons) {
            if (p.toString().equals(personName)) {
                return p;
            }
        }
        throw new NoSuchElementException(
                "This person does not exist in the database.");
    }

    /**
     * Gets a person given his/her name and whether he/she is wearing a mask
     * @param personName    The name of the person
     * @param isWearingMask Whether the person is wearing a mask
     * @return              The resulting person
     */
    Person getPerson(String personName, boolean isWearingMask) {
        for (Person p : listOfPersons) {
            if (p.toString().equals(personName)) {
                return p;
            }
        }
 
        // Since the person doesn't exist, create the object
        Person p;
        if (isWearingMask) {
            p = new MaskedPerson(personName);
        } else {
            p = new Person(personName);
        }

        // Infect the first person with an AlphaCoronavirus
        if (listOfPersons.size() == 0) {
            p = p.infectWith(List.of(new AlphaCoronavirus(0.8)), 1);
        }
        this.listOfPersons.add(p);
        return p;
    }

    /** 
     * Updates the state of a {@link Person}
     * @param person The new person state to update
     */
    void update(Person person) {
        for (int j = 0; j < this.listOfPersons.size(); ++j) {
            Person oldPerson = this.listOfPersons.get(j);
            if (oldPerson.toString().equals(person.toString())) {
                this.listOfPersons.remove(oldPerson);
                this.listOfPersons.add(j, person);
                break;
            }
        }
    }

    /**
     * Updates a list of new person's states
     * @param listOfPeople The list of {@link Person} to update
     */
    void update(List<? extends Person> listOfPeople) {
        for (int i = 0; i < listOfPeople.size(); ++i) {
            this.update(listOfPeople.get(i));
        }
    }

    /**
     * Gets all the people in the database
     * @return the entire database
     */
    List<? extends Person> getAllPeople() {
        return List.copyOf(this.listOfPersons);
    }

}

