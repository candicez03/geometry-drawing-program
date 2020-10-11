import java.awt.Point;

public class Quadrilateral extends SimplePolygon {
  public static final String[] PROMPTS = new String[] {
    "x coordinate of point 1",
    "y coordinate of point 1",
    "x coordinate of point 2",
    "y coordinate of point 2",
    "x coordinate of point 3",
    "y coordinate of point 3",
    "x coordinate of point 4",
    "y coordinate of point 4"
  };

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  
  private static final String TYPE = "quadrilateral";

  public Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
    super(p1, p2, p3, p4);
  }

  @Override
  public String getType() {
    return Quadrilateral.TYPE;
  }
}
