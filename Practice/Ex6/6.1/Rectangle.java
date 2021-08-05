public class Rectangle extends Shape {

    protected double width = 1.0;
    protected double length = 1.0;

    public Rectangle() {
        super();
    }

    public Rectangle(double width, double length) {
        super();
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return this.length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getArea() {
        return getLength() * getWidth();
    }

    public double getPerimeter() {
        return (2 * getLength()) + (2 * getWidth());
    }

    @Override
    public String toString() {
        return "Rectangle[" +
                super.toString() +
                ", width=" +
                this.getWidth() +
                ",length=" +
                this.getLength() +
                "]";
    }
}