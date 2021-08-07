public class ResizableCircle extends Circle implements Resizable {

  public ResizableCircle(double radius) {
    super(radius);
  }

  public String toString() {
    return "ResizableCircle[" +
      super.toString() +
      "]";
  }

  @Override
  public void resize(int percent) {
    setRadius(getRadius() * (double) (1 + (percent / 100)));
  }

}
