@SuppressWarnings("serial")
public class InvalidShapeException extends Exception {
  public InvalidShapeException() {
    super();
  }

  public InvalidShapeException(String errorMsg) {
    super(errorMsg);
  }
}
