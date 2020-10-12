import java.awt.Point;

/**
 * Represents a parallelogram by four vertex points.
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class Parallelogram extends Quadrilateral {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Circle} class's type. */
  private static final String TYPE = "parallelogram";

  /**
   * Constructs a new {@code Parallelogram} with
   * the top-left vertex, the bottom-left vertex and base width.
   * @param topLeft     The top-left vertex of the parallelogram.
   * @param bottomLeft  The bottom-left vertex of the parallelogram.
   * @param baseWidth   The base width of the parallelogram.
   */
  public Parallelogram(Point topLeft, Point bottomLeft, int baseWidth) {
    super(
      new Point(topLeft.x, topLeft.y),
      new Point(topLeft.x+baseWidth, topLeft.y),
      new Point(bottomLeft.x+baseWidth, bottomLeft.y),
      new Point(bottomLeft.x, bottomLeft.y)
      );
  }

  
  /** 
   * Returns the string representation of
   * the {@code Parallelogram} class's type.
   * @return String, the type of the {@code Parallelogram}.
   */
  @Override
  public String getType() {
    return Parallelogram.TYPE;
  }
}
