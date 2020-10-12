/**
 * Thrown when an attempt is made to:
 * <ul>
 * <li> Create a {@code Shape2D} with an area or perimeter equal to 0.
 * <li> Create a {@code Shape2D} that does not match its definition.
 *      Example: a complex polygon constructed with the SimplePolygon class.
 * </ul>
 * <p> created <b>2020.10.05</b>
 * @version 1.0
 * @author Candice Zhang
 * @see Shape2D
 */

@SuppressWarnings("serial")
public class InvalidShapeException extends Exception {
  /** The invalid shape that was attempted to be created. */
  Shape2D invalidShape;

  /**
   * [InvalidShapeException]
   * Constructs an {@code InvalidShapeException} with a reason message
   * and the invalid shape that was attempted to be created.
   * @see Shape2D
   */
  public InvalidShapeException(String reason, Shape2D invalidShape) {
    super(reason);
  }

  
  /** 
   * Returns the invalid shape that was attempted to be created.
   * @return Shape2D, the invalid shape that was attempted to be created.
   * @see Shape2D
   */
  public Shape2D getInvalidShape() {
    return this.invalidShape;
  }
}
