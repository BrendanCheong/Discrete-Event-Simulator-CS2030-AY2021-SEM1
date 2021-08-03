class Point3D extends Point2D {

  private float z = 0.0f;

  public Point3D(float x, float y, float z) {
    super(x, y);
    this.z = z;
  }

  public float getZ() {
    return this.z;
  }

  public void setZ(float z) {
    this.z = z;
  }

  public void setXYZ(float x, float y, float z) {
    super.setX(x);
    super.setY(y);
    this.setZ(z);
  }

  public float[] getXYZ() {
    float[] XYZcoord = {super.getX(), super.getY(), this.getZ()};
    return XYZcoord;
  }

  @Override
  public String toString() {
    return "(" +
      super.getX() +
      ", " +
      super.getY() +
      "," +
      this.getZ() +
      ")";
  }

}
