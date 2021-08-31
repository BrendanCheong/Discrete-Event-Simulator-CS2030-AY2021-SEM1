public class Rectangle {

    protected final double width;
    protected final double height;

    public Rectangle(double x, double y) {
        this.width = x;
        this.height = y;
    }

    public double getArea() {
        return this.width * this.height;
    }

    public Rectangle scaleWidthBy(double factor) {
        return new Rectangle(this.width * factor, this.height);
    }

    public Rectangle scaleHeightBy(double factor)  {
        return new Rectangle(this.width, this.height * factor);
    }

    @Override
    public String toString() {
        return "Rectangle: " + this.width + " x " + this.height;
    }

}
