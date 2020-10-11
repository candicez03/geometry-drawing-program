import java.awt.Point;

public class Triangle extends SimplePolygon {
  public static final String[] PROMPTS = new String[] {
    "x coordinate of point 1",
    "y coordinate of point 1",
    "x coordinate of point 2",
    "y coordinate of point 2",
    "x coordinate of point 3",
    "y coordinate of point 3"
  };

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  
  private static final String TYPE = "triangle";

  public Triangle(Point p1, Point p2, Point p3) {
    super(p1, p2, p3);
  }

  @Override
  public String getType() {
    return Triangle.TYPE;
  }
}