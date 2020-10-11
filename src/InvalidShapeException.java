@SuppressWarnings("serial")
public class InvalidShapeException extends Exception {
  Shape2D invalidShape;

  public InvalidShapeException(Shape2D invalidShape) {
    super();
  }
  
  public Shape2D getInvalidShape() {
    return this.invalidShape;
  }
}
