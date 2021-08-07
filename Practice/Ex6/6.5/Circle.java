public class Circle implements GeometricObject {

  private double radius;

  public Circle(double radius) {
    this.radius = radius;
  }

  public double getRadius() {
    return this.radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public String toString() {
    return "Circle[radius=" +
      this.radius +
      "]";
  }

  @Override
  public double getPerimeter() {
    return 2 * this.radius * Math.PI;
  }

  @Override
  public double getArea() {
    return Math.PI * Math.pow(this.radius, 2);
  }


}
