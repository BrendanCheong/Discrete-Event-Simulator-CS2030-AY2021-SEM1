class Rectangle {

  private float length;
  private float width;

  public Rectangle() {
    length = 1.0f;
    width = 1.0f;
  }

  public Rectangle(float newLength, float newWidth) {
    this.length = newLength;
    this.width = newWidth;
  }

  // Return Statements must have Type attached
  public float getLength() {
    return this.length;
  }

  // Void Statements must NOT have Type attached
  public void setLength(float length) {
    this.length = length;
  }

  public float getWidth() {
    return this.width;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public double getArea() {
    return this.length * this.width;
  }

  public double getPerimeter() {
    return (this.length * 2) + (this.width * 2);
  }

  public String toString() {
    return "Rectangle[length= " + this.length + ", width= " + this.width + "]";
  }
}
