public class Square extends Rectangle {

    public Square(double x) {
        super(x, x);
    }

    /**
     * Is the Override tag required in this case?
     * What is being overriden in this case?
     * Ans: There is overriding, Square does override Rectangle with @Override
     * Now what happens if I do Rectangle newR = new Square(5) with @Override?
     * newR,scaleHeightBy(2) returns a square!
     * Usually this is ok, but this leads to BUGS, what if the client consistently works
     * with Rectange newR = new Rectangle(5.0, 5.0)? The client expects a rectangle
     * but ends up with a square due to Polymorphism
     * **ALWAYS KNOW WHAT THE CLIENT EXPECTS** In this case we don't want @Override
     * We want to maintain a sqaure as a square and rectangle as a rectangle
     * */
    //@Override
    public Square scaleWidthBy(double factor) {
        return new Square(this.width * factor);
    }

    //@Override
    public Square scaleHeightBy(double factor)  {
        return new Square(this.height * factor);
    }

    @Override
    public String toString() {
        return "Square: " + this.width + " x " + this.height;
    }
}
