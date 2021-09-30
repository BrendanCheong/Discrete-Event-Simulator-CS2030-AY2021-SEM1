public class Route {
    
    private final City city1;
    private final City city2;
    private final double distance;

    public Route(City city1, City city2, double distance) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }
    @Override   
    public String toString() {
        return String.format("%s and %s is %.1fkm apart", 
            this.city1.getName(), this.city2.getName(), this.distance);
    }

    public City getCity1() {
        return this.city1;
    }

    public City getCity2() {
        return this.city2;
    }

    public double getDistance() {
        return this.distance;
    }
}
