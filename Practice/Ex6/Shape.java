public abstract class Shape {

    protected String color = "red";
    protected Boolean filled = true;

    public Shape() {

    }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean isFilled() {
        return this.filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public abstract double getArea() {

    }

    public abstract double getPerimeter() {

    }

    public abstract String toString() {
        return "Shape[color=" +
        this.getColor() +
        ", filled=" +
        this.getFilled() +
        "]";
    }
}