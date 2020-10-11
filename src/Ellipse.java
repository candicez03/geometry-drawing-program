import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Ellipse extends Shape2D {
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
  
  private static final String TYPE = "ellipse";

  private int x;
  private int y;
  private int width;
  private int height;

  public Ellipse(int x, int y, int width, int height) {
    super();
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.updateArea();
    this.updatePerimeter();
  }

  @Override
  public String getType() {
    return Ellipse.TYPE;
  }

  @Override
  public String toString() {
    // location: top left point of the square the circle is inscribed in
    String additionalInfo = String.format(
      "location: (%d, %d)\twidth: %d\theight: %d",
      this.x,
      this.y,
      this.width,
      this.height
    );
    return super.toString() + "\n" + additionalInfo;
  }

  @Override
  public double calculatePerimeter() {
    //Ramanujan's approximation
    double a = this.width/2.0;
    double b = this.height/2.0;
    return Math.PI * ( 3*(a+b) - Math.sqrt( (3*a + b) * (a + 3*b) ) );
  }

  @Override
  public double calculateArea() {
    double a = this.width/2.0;
    double b = this.height/2.0;
    return Math.PI * a * b;
  }

  @Override
  public void translateX(int value) {
    this.x += value;
  }

  @Override
  public void translateY(int value) {
    this.y += value;
  }

  @Override
  public void draw(Graphics2D g2d) {
    if (this.getRotationAngle() == 0) {
      g2d.drawOval(this.x, this.y, this.width, this.height);
      return;
    }

    AffineTransform transformCopy = g2d.getTransform();
    g2d.rotate(Math.toRadians(-this.getRotationAngle()));
    g2d.drawOval(this.x, this.y, this.width, this.height);
    g2d.setTransform(transformCopy);
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
