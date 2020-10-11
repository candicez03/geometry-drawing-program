import java.awt.Point;

/**
 * Represents a triangle by three vertex points.
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class Triangle extends SimplePolygon {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Circle} class's type. */
  private static final String TYPE = "triangle";

  /**
   * Constructs a new {@code Triangle} with
   * three vertex points.
   * @param p1 The first vertex of the triangle.
   * @param p2 The second vertex of the triangle.
   * @param p3 The third vertex of the triangle.
   */
  public Triangle(Point p1, Point p2, Point p3) {
    super(p1, p2, p3);
  }

  
  /** 
   * Returns the string representation of
   * the {@code Triangle} class's type.
   * @return String, the type of the {@code Triangle}.
   */
  @Override
  public String getType() {
    return Triangle.TYPE;
  }
}