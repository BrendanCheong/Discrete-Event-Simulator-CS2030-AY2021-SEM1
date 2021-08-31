public class Square extends Rectangle {

    public Square(double x) {
        super(x, x);
    }


    public Square scaleWidthBy(double factor) {
        return new Square(this.width * factor);
    }

    public Square scaleHeightBy(double factor)  {
        return new Square(this.height * factor);
    }

    @Override
    public String toString() {
        return "Square: " + this.width + " x " + this.height;
    }
}
