import java.awt.Point;

public class Triangle extends SimplePolygon {
  private static final String TYPE = "triangle";

  public Triangle(Point p1, Point p2, Point p3) {
    super(p1, p2, p3);
  }

  @Override
  public String getType() {
    return Triangle.TYPE;
  }
}