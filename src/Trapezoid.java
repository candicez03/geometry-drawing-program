import java.awt.Point;

/**
 * Represents a trapezoid by four vertex points.
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class Trapezoid extends Quadrilateral {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Circle} class's type. */
  private static final String TYPE = "trapezoid";

  /**
   * Constructs a new {@code Trapezoid} with
   * the top-left vertex, the bottom-left vertex,
   * top width and bottom width.
   * @param topLeft       The top-left vertex of the trapezoid.
   * @param bottomLeft    The bottom-left vertex of the trapezoid.
   * @param topWidth      The top width of the trapezoid.
   * @param bottomWidth   The bottom width of the trapezoid.
   */
  public Trapezoid(Point topLeft, Point bottomLeft, int topWidth, int bottomWidth) {
    super(
      topLeft,
      bottomLeft,
      new Point(bottomLeft.x + bottomWidth, bottomLeft.y),
      new Point(topLeft.x + topWidth, topLeft.y));
  }

  
  /** 
   * Returns the string representation of
   * the {@code Trapezoid} class's type.
   * @return String, the type of the {@code Trapezoid}.
   */
  @Override
  public String getType() {
    return Trapezoid.TYPE;
  }
}