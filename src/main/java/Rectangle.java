import java.awt.Point;

public class Rectangle extends Parallelogram {
  private static final String TYPE = "rectangle";

  private int width;
  private int height;

  public Rectangle(int x, int y, int width, int height) {
    super(
      new Point(x, y),
      new Point(x, y+height),
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
  public double calculateArea() {
    return this.width * this.height;
  }

  @Override
  public double calculatePerimeter() {
    return 2 * (this.width + this.height);
  }
}
