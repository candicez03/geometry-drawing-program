import java.awt.Point;

/**
 * Represents a rhombus by four vertex points.
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class Rhombus extends Parallelogram {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Rhombus} class's type. */
  private static final String TYPE = "rhombus";

  /**
   * Constructs a new {@code Rhombus} with
   * the top-left vertex and the bottom-left vertex.
   * @param topLeft     The top-left vertex of the rhombus.
   * @param bottomLeft  The bottom-left vertex of the rhombus.
   */
  public Rhombus(Point topLeft, Point bottomLeft) {
    super(
      topLeft,
      bottomLeft,
      (int)(Math.round(Math.hypot(topLeft.x-bottomLeft.x, topLeft.y-bottomLeft.y)))
    );
  }

  
  /** 
   * Returns the string representation of
   * the {@code Rhombus} class's type.
   * @return String, the type of the {@code Rhombus}.
   */
  @Override
  public String getType() {
    return Rhombus.TYPE;
  }
}
