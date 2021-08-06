class Rectangle implements GeometricObject {

  private double width;
  private double length;

  public Rectangle(double width, double length) {
    this.width = width;
    this.length = length;
  }

  public String toString() {
    return "Rectangle[width=" +
      this.width +
      ", length=" +
      this.length +
      "]";
  }

  public double getArea() {
    return this.width * this.length;
  }

  public double getPerimeter() {
    return (2 * this.width) + (2 * this.length);
  }

  public double getCuboid(double height) {
    return getArea() * height;
  }

}
