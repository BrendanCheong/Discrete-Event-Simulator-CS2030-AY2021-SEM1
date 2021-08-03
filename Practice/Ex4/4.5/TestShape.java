class TestShape {

  public static void main(String[] args) {
    Shape shapeOne = new Shape("purple", false);
    System.out.println(shapeOne.toString());

    Circle circleOne = new Circle(5.0, "green", true);
    System.out.println(circleOne.toString());
    System.out.println(circleOne.getPerimeter());
    System.out.println(circleOne.getArea());

    Rectangle rectangleOne = new Rectangle(3.0, 5.0, "turquoise", false);
    System.out.println(rectangleOne.toString());

    Square squareOne = new Square(2.0, "black", true);
    System.out.println(squareOne.toString());
  }

}
