/**
 * Thrown when an attempt is made to:
 * <ul>
 * <li> Create a {@code Shape2D} with an area or perimeter equal to or less than 0.
 * </ul>
 * <p> created <b>2020.10.05</b>
 * @version 1.0
 * @author Candice Zhang
 */

@SuppressWarnings("serial")
public class InvalidShapeException extends Exception {
  /**
   * Constructs an {@code InvalidShapeException} with a reason message
   * and the invalid shape that was attempted to be created.
   * @param reason       A string specifying the reason of the exception.
   */
  public InvalidShapeException(String reason) {
    super(reason);
  }
}
