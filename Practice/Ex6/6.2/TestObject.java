public class TestObject {

  public static void main(String[] args) {
    GeometricObject obj;
    obj = new Circle(5.0);
    System.out.println(obj.getArea());
    obj = new Rectangle(5.0, 3.0);
    System.out.println(obj.getPerimeter());
    //System.out.println(obj.getCuboid(4.0));
    Rectangle trueRect = new Rectangle(5.0, 4.0);
    System.out.println(trueRect.getCuboid(3.0));
  }

}
