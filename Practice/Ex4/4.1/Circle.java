class Circle {
  private double radius = 1.0;
  private String color = "red";

  public Circle() {

  }

  public Circle(double radius) {
    this.radius = radius;
  }


  public Circle(double radius, String color) {
    this.radius = radius;
    this.color = color;
  }

  public double getRadius() {
    return this.radius;
  }

  public String getColor() {
    return this.color;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public double getArea() {
    return Math.PI * Math.pow(this.getRadius(), 2);
  }

  @Override
  public String toString() {
    return "Circle[radius=" +
      this.getRadius() +
      ", color=" +
      this.getColor() +
      "]";
  }
}
