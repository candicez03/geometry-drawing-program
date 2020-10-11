import java.awt.Point;

public class Parallelogram extends Quadrilateral {
  public static final String[] PROMPTS = new String[] {
    "x coordinate (top-left)",
    "y coordinate (top-left)",
    "x coordinate (bottom-left)",
    "y coordinate (bottom-left)",
    "base width"
  };

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private static final String TYPE = "parallelogram";

  public Parallelogram(Point topLeft, Point bottomLeft, int baseWidth) {
    super(
      new Point(topLeft.x, topLeft.y),
      new Point(topLeft.x+baseWidth, topLeft.y),
      new Point(bottomLeft.x+baseWidth, bottomLeft.y),
      new Point(bottomLeft.x, bottomLeft.y)
      );
  }

  @Override
  public String getType() {
    return Parallelogram.TYPE;
  }
}
