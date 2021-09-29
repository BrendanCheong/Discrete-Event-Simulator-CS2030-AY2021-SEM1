import java.util.List;
import java.util.ArrayList;

public class Person {

    public static final double MASK_EFFECTIVENESS = 0.6;
    private static final boolean MASKED = false;
    private final String name;
    private final List<Virus> viruses;

    public Person(String name) {
        this.name = name;
        this.viruses = new ArrayList<>(1000);
    }

    public Person(String name, List<Virus> listOfVirus) {
        this.name = name;
        this.viruses = listOfVirus;
    }

    public List<Virus> transmit(double random) {
        List<Virus> viroLoad = new ArrayList<>();
        for (Virus virus : this.viruses) {
            viroLoad.add(virus.spread(random));
        }

        return viroLoad;
    }

    public Person infectWith(List<Virus> listOfVirus, double random) {
        if (this.getMaskStatus() && random <= MASK_EFFECTIVENESS) {
            return new MaskedPerson(this.toString());
        } else if (this.getMaskStatus()) {
            return new MaskedPerson(this.toString(), listOfVirus);
        } else {
            return new Person(this.toString(), listOfVirus);
        }
    }

    public List<Virus> getViroLoad() {
        return this.viruses;
    }

    public boolean test(String name) {
        for (Virus virus : this.viruses) {
            if (virus.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean getMaskStatus() {
        return MASKED;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
