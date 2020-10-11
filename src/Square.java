import java.awt.Point;

public class Square extends Rectangle {  
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private static final String TYPE = "square";

  public Square(Point topLeft, int sideLength) {
    super(topLeft, sideLength, sideLength);
  }

  @Override
  public String getType() {
    return Square.TYPE;
  }

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
