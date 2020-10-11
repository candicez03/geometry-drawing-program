import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * Represents a polygon by a finite number of vertices
 * that are connected by straight line segments.
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public abstract class Polygon extends Shape2D {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Polygon} class's type. */
  private static final String TYPE = "polygon";

  /** The array that stores the x coordinates of the vertices. */
  private int[] xPoints;
  /** The array that stores the y coordinates of the vertices. */
  private int[] yPoints;

  /**
   * Constructs a new {@code Polygon} with a given set of vertices.
   * @param vertices  A array of vertex points of the polygon.
   */
  public Polygon(Point... vertices) {
    super();
    this.xPoints = new int[vertices.length];
    this.yPoints = new int[vertices.length];
    for (int i = 0; i < vertices.length; i++) {
      this.xPoints[i] = vertices[i].x;
      this.yPoints[i] = vertices[i].y;
    }
    this.updatePerimeter();
  }

  /** 
   * Returns the string representation of
   * the {@code Polygon} class's type.
   * @return String, the type of the {@code Polygon}.
   */
  @Override
  public String getType() {
    return Polygon.TYPE;
  }

  
  /** 
   * Returns a {@code String} representation of the {@code Polygon}.
   * <p>
   * Includes: type, area, perimeter, rotation angle,
   * coordinates of vertices.
   * @return String, a representation of the {@code Polygon}.
   */
  @Override
  public String toString() {
    String additionalInfo = "Points: [";
    for (int i = 0; i < this.xPoints.length; i++) {
      additionalInfo += String.format(
        "(%d, %d)",
        this.xPoints[i],
        this.yPoints[i]
      );
    }
    return super.toString() + "\n" + additionalInfo;
  }

  @Override
  public double calculatePerimeter() {
    double perimeter = 0;
    int pointNum = this.xPoints.length;
    for (int i = 0; i < pointNum; i++) {
      Point p1 = new Point(this.xPoints[i], this.yPoints[i]);
      Point p2 = new Point(this.xPoints[(i+1)%(pointNum-1)], this.yPoints[(i+1)%(pointNum-1)]);
      perimeter += Math.hypot(p1.x-p2.x, p1.y-p2.y);
    }
    return perimeter;
  }

  @Override
  public void translate(int dx, int dy) {
    for (int i = 0; i < this.xPoints.length; i++) {
      this.xPoints[i] += dx;
      this.yPoints[i] += dy;
    }
  }
  
  @Override
  public void draw(Graphics2D g2d) {
    if (this.getRotationAngle() == 0) {
      g2d.drawPolygon(this.xPoints, this.yPoints, this.xPoints.length);
      return;
    }

    AffineTransform transformCopy = g2d.getTransform();
    g2d.rotate(
      Math.toRadians(this.getRotationAngle()),
      this.xPoints[0],
      this.yPoints[0]
    );
    g2d.drawPolygon(this.xPoints, this.yPoints, this.xPoints.length);
    g2d.setTransform(transformCopy);
  }

  
  /** 
   * Returns an array of the x coordinates of the vertices.
   * @return int[], an array of the x coordinates of the vertices.
   */
  public int[] getXPoints() {
    return this.xPoints;
  }

  
  /** 
   * Returns an array of the y coordinates of the vertices.
   * @return int[], an array of the y coordinates of the vertices.
   */
  public int[] getYPoints() {
    return this.yPoints;
  }
}
