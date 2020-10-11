public class Circle extends Ellipse {
  public static final String[] PROMPTS = new String[] {
    "x coordinate (top-left)",
    "y coordinate (top-left)",
    "diameter",
  };

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private static final String TYPE = "circle";

  public Circle(int x, int y, int diameter) {
    super(x, y, diameter, diameter);
  }

  @Override
  public String getType() {
    return Circle.TYPE;
  }

  @Override
  public String toString() {
    // location: top left point of the square the circle is inscribed in
    String additionalInfo = String.format(
      "location: (%d, %d)\tdiameter: %d",
      this.getX(),
      this.getY(),
      this.getWidth()
    );
    return super.toString() + "\n" + additionalInfo;
  }
  
}
