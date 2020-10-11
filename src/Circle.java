import java.awt.Point;

public class Circle extends Ellipse {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private static final String TYPE = "circle";

  public Circle(Point topLeft, int diameter) {
    super(topLeft, diameter, diameter);
  }

  @Override
  public String getType() {
    return Circle.TYPE;
  }

  @Override
  public String toString() {
    // location: top left point of the square the circle is inscribed in
    String additionalInfo = String.format(
      "location: (%d, %d)\tdiameter: %d",
      this.getTopLeft().x,
      this.getTopLeft().y,
      this.getWidth()
    );
    return super.toString() + "\n" + additionalInfo;
  }
  
}
