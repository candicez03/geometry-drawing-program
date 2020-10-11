import java.awt.Point;

/**
 * Represents a square by four vertex points.
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class Square extends Rectangle {  
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Circle} class's type. */
  private static final String TYPE = "square";

  /**
   * Constructs a new {@code Square} with
   * the top-left vertex and side length.
   * @param topLeft       The top-left vertex of the square.
   * @param sideLength    The side length of the square.
   */
  public Square(Point topLeft, int sideLength) {
    super(topLeft, sideLength, sideLength);
  }

  /** 
   * Returns the string representation of
   * the {@code Square} class's type.
   * @return String, the type of the {@code Square}.
   */
  @Override
  public String getType() {
    return Square.TYPE;
  }

  
  /** 
   * Returns a {@code String} representation of the {@code Square}.
   * <p>
   * Includes: type, area, perimeter, rotation angle,
   * location and side length.
   * @return String, a representation of the {@code Square}.
   */
  @Override
  public String toString() {
    String additionalInfo = String.format(
      "location: (%d, %d)\tside length: %d",
      this.getTopLeft().x,
      this.getTopLeft().y,
      this.getHeight()
    );
    return super.toString() + "\n" + additionalInfo;
  }
}
