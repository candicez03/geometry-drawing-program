import java.awt.Point;

/**
 * Represents a simple polygon by a finite number of vertices
 * that are connected by straight line segments.
 * <p>
 * A simple polygon has only one boundary, and it does not cross over itself.
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class SimplePolygon extends Polygon {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code SimplePolygon} class's type. */
  private static final String TYPE = "simple polygon";

  /**
   * Constructs a new {@code SimplePolygon} with a given set of vertices.
   * @param vertices  A array of vertex points of the simple polygon.
   */
  public SimplePolygon(Point... vertices) throws IllegalArgumentException {
    super(vertices);
    if (!SimplePolygon.isSimplePolygon(vertices)) {
      throw new IllegalArgumentException("The given points do not form a simple polygon.");
    }
    this.updateArea();
  }

  /** 
   * Checks if a given array of Points forms a simple polygon in its order.
   * <p>
   * A simple polygon has only one boundary, and it does not cross over itself.
   * @param vertices  The array of Points to be checked.
   * @return boolean, {@code true} if the array of points forms
   * a simple polygon; {@code false} otherwise.
   */
  public static boolean isSimplePolygon(Point[] vertices) {
    // TODO: finish
    return true;
  }
  
  /** 
   * Returns the string representation of
   * the {@code SimplePolygon} class's type.
   * @return String, the type of the {@code SimplePolygon}.
   */
  @Override
  public String getType() {
    return SimplePolygon.TYPE;
  }

  /** 
   * @return double
   */
  @Override
  public double calculateArea() {
    // Gauss's area formula
    double area = 0;
    int[] xPoints = this.getXPoints();
    int[] yPoints = this.getYPoints();
    int numPoints = this.getXPoints().length;
    for (int i = 0; i < numPoints; i++) {
      int x1 = xPoints[i];
      int y1 = yPoints[i];
      int x2 = xPoints[(i+1)%(numPoints-1)];
      int y2 = yPoints[(i+1)%(numPoints-1)];
      area +=  x1*y2 - y1*x2;
    }
    return Math.abs(area/2);
  }
}
