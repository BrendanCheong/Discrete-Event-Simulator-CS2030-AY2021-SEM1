class Circle {
  private double radius;
  private String color;

  // Default Circle
  public Circle() {
    radius = 1.0;
    color = "red";
  }

  //Initialise Circle instance
  public Circle(double rad, String colour) {
    this.radius = rad;
    this.color = colour;
  }

  // Returns the Radius
  public double getRadius() {
    return radius;
  }

  // Returns the Area
  public double getArea() {
    return Math.pow(radius, 2) * Math.PI;
  }

  // Returns Color of Circle
  public String getColor() {
    return this.color;
  }

  // Mutates Color
  public void setColor(String newColour) {
    this.color = newColour;
  }

  // Mutates Radius
  public void setRadius(double newRadius) {
    this.radius = newRadius;
  }

  // ToString
  public String toString() {
    return "Circle[radius=" + radius +" color=" + color + "]";
  }

  public static void main (String[] args) {
    System.out.println("Wassup World");
    System.out.println("Java Always Needs a Complier!");
  }
}
