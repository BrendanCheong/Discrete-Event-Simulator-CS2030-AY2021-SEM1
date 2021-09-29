/**
 * <p>The TraceTogether Programme is a programme to enhance Singapore’s 
 * contact tracing efforts, in the fight against COVID-19. It comprises the
 * TraceTogether App and TraceTogether Token. The App was released on 20
 * March, while the Token was rolled out on 28 June. Both the App and Token
 * work by using Bluetooth signals to record other nearby TraceTogether
 * devices.
 * Source: support.tracetogether.gov.sg
 * <p>This class is meant to act as a simulated tracetogether
 * system, and is not the real TraceTogether platform.
 */
class TraceTogether {

    /**
     * Initialises TraceTogether
     */
    TraceTogether() {}

    /**
     * Indicate that two people have made contact
     * @param personA The first person in the contact
     * @param personB The second person in the contact
     * @param time    The time of contact
     */
    Contact contact(Person personA, Person personB, double time) {
        return new Contact(personA, personB, time);
    }

}

