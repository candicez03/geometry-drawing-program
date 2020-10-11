public class Square extends Rectangle {
  public static final String[] PROMPTS = new String[] {
    "x coordinate (top-left)",
    "y coordinate (top-left)",
    "side length"
  };
  
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private static final String TYPE = "square";

  public Square(int x, int y, int sideLength) {
    super(x, y, sideLength, sideLength);
  }

  @Override
  public String getType() {
    return Square.TYPE;
  }

  @Override
  public String toString() {
    String additionalInfo = String.format(
      "location: (%d, %d)\tside length: %d",
      this.getX(),
      this.getY(),
      this.getHeight()
    );
    return super.toString() + "\n" + additionalInfo;
  }
}
