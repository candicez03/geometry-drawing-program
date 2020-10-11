import java.awt.Point;

public class Rectangle extends Parallelogram {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private static final String TYPE = "rectangle";

  private int width;
  private int height;

  public Rectangle(Point topLeft, int width, int height) {
    super(
      topLeft,
      new Point(topLeft.x, topLeft.y+height),
      width
    );
    this.width = width;
    this.height = height;
  }

  @Override
  public String getType() {
    return Rectangle.TYPE;
  }

  @Override
  public String toString() {
    String additionalInfo = String.format(
      "location: (%d, %d)\twidth: %d, height: %d",
      this.getTopLeft().x,
      this.getTopLeft().y,
      this.width,
      this.height
    );
    return super.toString() + "\n" + additionalInfo;
  }

  @Override
  public double calculateArea() {
    return this.width * this.height;
  }

  @Override
  public double calculatePerimeter() {
    return 2 * (this.width + this.height);
  }

  public Point getTopLeft() {
    return new Point(this.getXPoints()[0], this.getYPoints()[0]);
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }
}
