import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public abstract class Polygon extends Shape2D {
  private static final String TYPE = "polygon";

  private int[] xPoints;
  private int[] yPoints;

  public Polygon(Point... vertices) {
    this.xPoints = new int[vertices.length];
    this.yPoints = new int[vertices.length];
    for(int i = 0; i < vertices.length; i++) {
      this.xPoints[i] = vertices[i].x;
      this.yPoints[i] = vertices[i].y;
    }
  }

  @Override
  public void translateX(int value) {
    for(int i = 0; i < this.xPoints.length; i++) {
      this.xPoints[i] += value;
    }
  }

  @Override
  public void translateY(int value) {
    for(int i = 0; i < this.yPoints.length; i++) {
      this.yPoints[i] += value;
    }
  }
  
  @Override
  public String getType() {
    return Polygon.TYPE;
  }

  @Override
  public double calculatePerimeter() {
      double perimeter = 0;
      int pointNum = this.xPoints.length;
      for(int i = 0; i < pointNum; i++) {
        Point p1 = new Point(this.xPoints[i], this.yPoints[i]);
        Point p2 = new Point(this.xPoints[(i+1)%(pointNum-1)], this.yPoints[(i+1)%(pointNum-1)]);
        perimeter += Math.hypot(p1.x-p2.x, p1.y-p2.y);
      }
      return perimeter;
  }

  @Override
  public void draw(Graphics g) {
    if (this.getRotationAngle() == 0) {
      g.drawPolygon(this.xPoints, this.yPoints, this.xPoints.length);
      return;
    }

    Graphics2D g2d = (Graphics2D)g;
    AffineTransform transformCopy = g2d.getTransform();
    g2d.rotate(Math.toRadians(this.getRotationAngle()));
    g.drawPolygon(this.xPoints, this.yPoints, this.xPoints.length);
    g2d.setTransform(transformCopy);
  }

  public int[] getXPoints() {
    return this.xPoints;
  }

  public int[] getYPoints() {
    return this.yPoints;
  }
}
