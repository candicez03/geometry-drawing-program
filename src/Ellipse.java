import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * Represents an ellipse by its width, height,
 * and the top-left point of the rectangle
 * it is inscribed in.
 * <p> created <b>2020-10-05</b>
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class Ellipse extends Shape2D {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Ellipse} class's type. */
  private static final String TYPE = "ellipse";
  
  /** The top-left point of the rectangle this ellipse is inscribed in. */
  private Point topLeft;
  /** The width of the ellipse. */
  private int width;
  /** The height of the ellipse. */
  private int height;

  /**
   * Constructs a new {@code Ellipse}.
   * @param topLeft   The top-left point of the rectangle
   *                  the ellipse is inscribed in.
   * @param width     The width of the circle.
   * @param height    The height of the circle.
   */
  public Ellipse(Point topLeft, int width, int height) {
    super();

    this.topLeft = topLeft;
    this.width = width;
    this.height = height;

    this.updateArea();
    this.updatePerimeter();
  }

  
  /** 
   * Returns the string representation of
   * the {@code Ellipse} class's type.
   * @return String, the type of the {@code Ellipse}.
   */
  @Override
  public String getType() {
    return Ellipse.TYPE;
  }

  
  /** 
   * Returns a {@code String} representation of the {@code Ellipse}.
   * <p>
   * Includes: type, area, perimeter, rotation angle,
   * location, width, height.
   * @return String, a representation of the {@code Ellipse}.
   */
  @Override
  public String toString() {
    // location: top left point of the square the circle is inscribed in
    String additionalInfo = String.format(
      "location: (%d, %d)\twidth: %d\theight: %d",
      this.topLeft.x,
      this.topLeft.y,
      this.width,
      this.height
    );
    return super.toString() + "\n" + additionalInfo;
  }

  @Override
  public double calculateArea() {
    double a = this.width/2.0;
    double b = this.height/2.0;
    return Math.PI * a * b;
  }

  @Override
  public double calculatePerimeter() {
    //Ramanujan's approximation
    double a = this.width/2.0;
    double b = this.height/2.0;
    return Math.PI * ( 3*(a+b) - Math.sqrt( (3*a + b) * (a + 3*b) ) );
  }

  @Override
  public void translate(int dx, int dy) {
    this.topLeft.translate(dx, dy);
  }

  @Override
  public void draw(Graphics2D g2d) {
    if (this.getRotationAngle() == 0) {
      g2d.drawOval(this.topLeft.x, this.topLeft.y, this.width, this.height);
      return;
    }

    AffineTransform transformCopy = g2d.getTransform();
    g2d.rotate(
      Math.toRadians(this.getRotationAngle()),
      this.topLeft.x,
      this.topLeft.y
    );
    g2d.drawOval(this.topLeft.x, this.topLeft.y, this.width, this.height);
    g2d.setTransform(transformCopy);
  }
  
  
  /** 
   * Returns the top-left point of the rectangle
   * this ellipse is inscribed in. 
   * @return Point, the top-left point.
   */
  public Point getTopLeft() {
    return this.topLeft;
  }

  
  /** 
   * Returns the width of the ellipse.
   * @return int
   */
  public int getWidth() {
    return this.width;
  }

  
  /** 
   * Returns the height of the ellipse.
   * @return int
   */
  public int getHeight() {
    return this.height;
  }
}
