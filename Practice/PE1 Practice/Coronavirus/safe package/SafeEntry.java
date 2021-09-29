import java.util.List;
import java.util.ArrayList;

/**
 * <p>SafeEntry is a national digital check-in system that logs individuals
 * visiting hotspots, workplaces of essential services, as well as selected
 * public venues to prevent and control the transmission of disease through
 * activities such as contact tracing and identification of disease clusters.
 * Source: safeentry.gov.sg
 *
 * <p>This class is not the real safeentry platform
 */
class SafeEntry {

    private final LocationDatabase locationDatabase = new LocationDatabase();

    /**
     * Initialises SafeEntry
     */
    SafeEntry() {}

    /**
     * Checks a person in to the location
     * @param person   The person checking in
     * @param location The location the person is checking into
     * @param time     The time of check in
     * @return         a list of all the new contacts made
     */
    List<Contact> checkIn(Person person, String location, double time) {
        Location l = locationDatabase.getLocation(location);
        List<? extends Person> personsInLocation = l.getOccupants();
        List<Contact> allContacts = new ArrayList<>();
        for (Person p : personsInLocation) {
            allContacts.add(new Contact(person, p, time));
        }
        locationDatabase.update(l.accept(person));
        return allContacts;
    }

    /**
     * Checks a person out of a location
     * @param person    The person checking out
     * @param location  The name of the location the person is checking out of
     */
    void checkOut(Person person, String location) {
        Location l = locationDatabase.getLocation(location);
        locationDatabase.update(l.remove(person.toString()));
    }

}

