import java.awt.Point;

public class Rectangle extends Parallelogram {
  public static final String[] PROMPTS = new String[] {
    "x coordinate (top-left)",
    "y coordinate (top-left)",
    "width",
    "height"
  };

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private static final String TYPE = "rectangle";

  private int x;
  private int y;
  private int width;
  private int height;

  public Rectangle(int x, int y, int width, int height) {
    super(
      new Point(x, y),
      new Point(x, y+height),
      width
    );
    this.x = x;
    this.y = y;
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
      this.x,
      this.y,
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

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }
}
