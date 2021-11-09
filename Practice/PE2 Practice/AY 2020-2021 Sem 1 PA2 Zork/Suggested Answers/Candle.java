import java.util.List;

public class Candle extends Thing {
    public Candle() {
       this(0);
    }

    public Candle(int state) {
        super(List.of("Candle flickers.","Candle is getting shorter.","Candle is about to burn out.","Candle has burned out."),state);
    }

    @Override
    public Thing tick() {
        if (super.getState()+1 == super.getEvents().size()) {
            return this;
        }

        return new Candle(super.getState()+1);
    }
}

