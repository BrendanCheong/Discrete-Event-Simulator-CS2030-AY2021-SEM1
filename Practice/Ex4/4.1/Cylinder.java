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

  public double getVolume() {
    return this.getHeight() * this.getArea();
  }

}
