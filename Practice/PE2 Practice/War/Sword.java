import java.util.List;

public class Sword extends Thing {
    private final boolean isEquipped;

    public Sword(int state,boolean isEquipped) {
        super(List.of("Sword is shimmering."),state);
        this.isEquipped= isEquipped;
    }

    public Sword() {
        this(0,false);
    }

    public Sword(int state) {
        this(state,false);
    }

    public boolean isEquipped() {
        return this.isEquipped;
    }

    public Sword equipSword() {
        return new Sword(super.getState(),true);
    }

    public Sword unequipSword() {
        return new Sword(super.getState(),false);
    }

    @Override
    public Thing tick() {
        if (super.getState()+1 == super.getEvents().size()) {
            return this;
        }
        return new Sword(super.getState()+1);
    }

}
