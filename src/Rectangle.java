import java.awt.Point;

/**
 * Represents a rectangle by four vertex points.
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class Rectangle extends Parallelogram {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Circle} class's type. */
  private static final String TYPE = "rectangle";

  /** The width of the rectangle. */
  private int width;
  /** The height of the rectangle. */
  private int height;

  /**
   * Constructs a new {@code Rectangle} with
   * the top-left vertex, width and height.
   * @param topLeft   The top-left vertex of the rectangle.
   * @param width     The width of the rectangle.
   * @param height    The height of the rectangle.
   */
  public Rectangle(Point topLeft, int width, int height) {
    super(
      topLeft,
      new Point(topLeft.x, topLeft.y+height),
      width
    );
    this.width = width;
    this.height = height;
  }

  
  /** 
   * Returns the string representation of
   * the {@code Rectangle} class's type.
   * @return String, the type of the {@code Rectangle}.
   */
  @Override
  public String getType() {
    return Rectangle.TYPE;
  }

  
  /** 
   * Returns a {@code String} representation of the {@code Rectangle}.
   * <p>
   * Includes: type, area, perimeter, rotation angle,
   * location, width and height.
   * @return String, a representation of the {@code Rectangle}.
   */
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

  
  /** 
   * Returns the top-left vertex of the rectangle.
   * @return Point, the top-left vertex of the rectangle.
   */
  public Point getTopLeft() {
    return new Point(this.getXPoints()[0], this.getYPoints()[0]);
  }

  
  /** 
   * Returns the width of the rectangle.
   * @return int, the width of the rectangle.
   */
  public int getWidth() {
    return this.width;
  }

  
  /** 
   * Returns the height of the rectangle.
   * @return int, the height of the rectangle.
   */
  public int getHeight() {
    return this.height;
  }
}
