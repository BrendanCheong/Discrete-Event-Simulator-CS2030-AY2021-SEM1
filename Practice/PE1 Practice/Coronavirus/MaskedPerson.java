import java.util.List;
import java.util.ArrayList;

public class MaskedPerson extends Person {

    public static final double MASK_EFFECTIVENESS = 0.6;
    public static final boolean MASKED = true;

    public MaskedPerson(String name) {
        super(name);
    }

    public MaskedPerson(String name, List<Virus> listOfVirus) {
        super(name, listOfVirus);
    }

    @Override
    public List<Virus> transmit(double random) {
        if (random <= MASK_EFFECTIVENESS) {
            return new ArrayList<Virus>(1000);
        } else {
            return super.transmit(random);
        }
    }

    @Override
    public Person infectWith(List<Virus> listOfVirus, double random) {
        if (random <= MASK_EFFECTIVENESS) {
            return new MaskedPerson(this.toString(), new ArrayList<Virus>(1000));
        } else {
            return super.infectWith(listOfVirus, random);
        }
    }
}
