@SuppressWarnings("serial")
public class InvalidShapeException extends Exception {
  Shape2D invalidShape;

  public InvalidShapeException(String reason, Shape2D invalidShape) {
    super(reason);
  }

  
  /** 
   * @return Shape2D
   */
  public Shape2D getInvalidShape() {
    return this.invalidShape;
  }
}
