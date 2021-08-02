import java.lang.Math;

class MyPoint {

  private int x = 0;
  private int y = 0;

  public MyPoint() {

  }

  public MyPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int[] getXY() {
    int[] coordinates = {this.getX(), this.getY()};
    return coordinates;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    return "(" +
      this.getX() +
      ", " +
      this.getY() +
      ")";
  }

  public double distance(int x, int y) {
    double firstXsquare = Math.pow(this.getX() - x, 2);
    double firstYsquare = Math.pow(this.getY() - y, 2);
    return Math.sqrt(firstXsquare + firstYsquare);
  }

  public double distance(MyPoint another) {
    double firstXsquare = Math.pow(this.getX() - another.getX(), 2);
    double firstYsquare = Math.pow(this.getY() - another.getY(), 2);
    return Math.sqrt(firstXsquare + firstYsquare);
  }

  public double distance() {
    double firstXsquare = Math.pow(this.getX(), 2);
    double firstYsquare = Math.pow(this.getY(), 2);
    return Math.sqrt(firstXsquare + firstYsquare);
  }

}
