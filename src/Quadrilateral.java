import java.awt.Point;

/**
 * Represents a quadrilateral by four vertex points.
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public class Quadrilateral extends SimplePolygon {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;
  /** The string representation of the {@code Quadrilateral} class's type. */
  private static final String TYPE = "quadrilateral";

  /**
   * Constructs a new {@code Quadrilateral} with 4 given vertices.
   * @param p1 The first vertex of the quadrilateral.
   * @param p2 The second vertex of the quadrilateral.
   * @param p3 The third vertex of the quadrilateral.
   * @param p4 The fourth vertex of the quadrilateral.
   */
  public Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
    super(p1, p2, p3, p4);
  }

  
  /** 
   * Returns the string representation of
   * the {@code Quadrilateral} class's type.
   * @return String, the type of the {@code Quadrilateral}.
   */
  @Override
  public String getType() {
    return Quadrilateral.TYPE;
  }
}
