import java.util.List;

public class Troll extends Thing {
    public Troll() {
        this(0);
    }

    public Troll(int state) {
        super(List.of(
          "Troll lurks in the shadows.",
          "Troll is getting hungry.",
          "Troll is VERY hungry.",
          "Troll is SUPER HUNGRY and is about to ATTACK!",
          "Troll attacks!"),state);
    }

    @Override
    public Thing tick() {
        if (super.getState()+1 == super.getEvents().size()) {
            return this;
        }

        return new Troll(super.getState()+1);
    }
}

