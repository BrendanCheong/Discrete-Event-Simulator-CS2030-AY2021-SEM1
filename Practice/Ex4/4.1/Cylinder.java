public class Cylinder extends Circle {

  private double height = 1.0;

  public Cylinder() {
    super();
  }

  public Cylinder(double radius) {
    super(radius); // creates a Cyclinder with the same attributes as Circle
  }

  public Cylinder(double radius, double height) {
    super(radius);
    this.height = height;
  }

  public Cylinder(double radius, double height, String color) {
    super(radius, color);
    this.height = height;
  }

  public double getHeight() {
    return this.height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  // this is to get SURFACE AREA of the Cylinder
  public double getArea() {
    return (super.getArea() * 2) +
      (2 * Math.PI * this.getHeight() * this.getRadius());
  }

  public double getVolume() {
    return this.getHeight() * super.getArea();
  }
  // We want to use override Circle's getArea with our own Surface Area Equation
  // However, getVolume must use Circle's getArea not this(our Surface Area)
  // so we use super.getArea to solve this

}
