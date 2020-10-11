import java.awt.Point;

public class Parallelogram extends Quadrilateral {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Circle} class's type. */
  private static final String TYPE = "parallelogram";

  public Parallelogram(Point topLeft, Point bottomLeft, int baseWidth) {
    super(
      new Point(topLeft.x, topLeft.y),
      new Point(topLeft.x+baseWidth, topLeft.y),
      new Point(bottomLeft.x+baseWidth, bottomLeft.y),
      new Point(bottomLeft.x, bottomLeft.y)
      );
  }

  
  /** 
   * @return String
   */
  @Override
  public String getType() {
    return Parallelogram.TYPE;
  }
}
