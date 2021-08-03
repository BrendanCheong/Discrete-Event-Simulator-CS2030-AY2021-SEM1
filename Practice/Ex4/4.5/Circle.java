class Circle extends Shape {

  private double radius = 1.0;

  public Circle() {
    super();

  }

  public Circle(double radius) {
    super();
    this.radius = radius;
  }

  public Circle(double radius, String color, boolean filled) {
    super(color, filled);
    this.radius = radius;
  }

  public double getRadius() {
    return this.radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public double getArea() {
    return Math.PI * Math.pow(this.getRadius(), 2);
  }

  public double getPerimeter() {
    return 2 * Math.PI * this.getRadius();
  }

  @Override
  public String toString() {
    return "Circle[" +
      super.toString() +
      ",radius=" +
      this.getRadius() +
      "]";
  }

}
