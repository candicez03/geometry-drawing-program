import java.awt.Point;

public class Rhombus extends Parallelogram {
  public static final String[] PROMPTS = new String[] {
    "x coordinate (top-left)",
    "y coordinate (top-left)",
    "x coordinate (bottom-left)",
    "y coordinate (bottom-left)"
  };

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private static final String TYPE = "rhombus";

  public Rhombus(Point topLeft, Point bottomLeft) {
    super(
      topLeft,
      bottomLeft,
      (int)(Math.round(Math.hypot(topLeft.x-bottomLeft.x, topLeft.y-bottomLeft.y)))
    );
  }

  @Override
  public String getType() {
    return Rhombus.TYPE;
  }
}
