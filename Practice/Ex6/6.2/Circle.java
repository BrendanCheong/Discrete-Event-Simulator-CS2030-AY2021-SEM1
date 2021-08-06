class Circle implements GeometricObject {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public String toString() {
        return "Circle[radius=" +
                this.radius +
                "]";
    }

    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

}