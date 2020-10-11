import java.awt.Point;

public class Trapezoid extends Quadrilateral {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  
  private static final String TYPE = "trapezoid";

  public Trapezoid(Point topLeft, Point bottomLeft, int topLength, int bottomLength) {
    super(
      topLeft,
      bottomLeft,
      new Point(bottomLeft.x + bottomLength, bottomLeft.y),
      new Point(topLeft.x + topLength, topLeft.y));
  }

  @Override
  public String getType() {
    return Trapezoid.TYPE;
  }
}