import java.awt.Point;

/**
 * Represents a circle by its diameter and
 * the top-left point of the square
 * it is inscribed in.
 * <p> created <b>2020-10-05</b>
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class Circle extends Ellipse {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Circle} class's type. */
  private static final String TYPE = "circle";

  /**
   * Constructs a new {@code Circle}.
   * @param topLeft   The top-left point of the square
   *                  the circle is inscribed in.
   * @param diameter  The diameter of the circle.
   */
  public Circle(Point topLeft, int diameter) {
    super(topLeft, diameter, diameter);
  }

  
  /** 
   * Returns the string representation of
   * the {@code Circle} class's type.
   * @return String, the type of the {@code Circle}.
   */
  @Override
  public String getType() {
    return Circle.TYPE;
  }

  
  /** 
   * Returns a {@code String} representation of the {@code Circle}.
   * <p>
   * Includes: type, area, perimeter, rotation angle,
   * location, diameter.
   * @return String, a representation of the {@code Circle}.
   */
  @Override
  public String toString() {
    String additionalInfo = String.format(
      "location: (%d, %d)\tdiameter: %d",
      this.getTopLeft().x,
      this.getTopLeft().y,
      this.getWidth()
    );
    return super.toString() + "\n" + additionalInfo;
  }
  
}
