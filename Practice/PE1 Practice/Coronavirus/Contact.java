import java.util.List;

public class Contact {

    private final Person first;
    private final Person second;
    private final double time;

    public Contact(Person first, Person second, double time) {
        this.first = first;
        this.second = second;
        this.time = time;
    }

    public List<Person> transmit(double random) {
        // first a must spread to b
        // then b spread to a
        // create a new Person for new Virus List
        List<Virus> virusFromA = this.first.transmit(random);
        List<Virus> virusFromB = this.second.transmit(random);
        virusFromB.addAll(this.first.getViroLoad());
        virusFromA.addAll(this.second.getViroLoad());
        Person mutatedAZombie = new Person(this.first.toString(), virusFromB);
        Person mutatedBZombie = new Person(this.second.toString(), virusFromA);
        List<Person> infectedZombies = List.of(mutatedAZombie, mutatedBZombie);
        return infectedZombies;
    }

    public List<Person> getPeople() {
        List<Person> thePeople = List.of(first, second);
        return thePeople;
    }

    public double timeOfContact() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("%s contacted %s at %.3f time",
            this.first.toString(), this.second.toString(), this.time);
    }
}
