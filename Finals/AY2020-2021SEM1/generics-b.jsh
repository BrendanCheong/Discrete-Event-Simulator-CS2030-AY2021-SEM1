public class Melon implements Comparable<Melon> {

    private final int sugar;
    private final String name;

    public Melon(int sugar, String name) {
        this.sugar = sugar;
        this.name = name;
    }

    public int getSugar() {
        return this.sugar;
    }

    public int compareTo(Melon melon) {
        return this.sugar - melon.getSugar();
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class WaterMelon extends Melon {

    public WaterMelon(int sugar, String name) {
        super(sugar, name);
    }
}

public class WinterMelon extends Melon {
    
    public WinterMelon(int sugar, String name) {
        super(sugar, name);
    }
}

List<Melon> waterMelons = new ArrayList<>(List.of(new WaterMelon(10, "yellow WaterMelon"), new WaterMelon(20, "green WaterMelon")));
List<Melon> winterMelons = new ArrayList<>(List.of(new WinterMelon(1, "red winterMelon"), new WinterMelon(2, "purple winterMelon")));
replace(waterMelons, winterMelons)
winterMelons