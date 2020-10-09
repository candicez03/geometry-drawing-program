package src.main.java;

import java.awt.Point;

public class Parallelogram extends Quadrilateral {
  private static final String TYPE = "parallelogram";

  public Parallelogram(Point topLeft, Point bottomLeft, int baseWidth) {
    super(
      new Point(topLeft.x, topLeft.y),
      new Point(topLeft.x+baseWidth, topLeft.y),
      new Point(bottomLeft.x, bottomLeft.y),
      new Point(bottomLeft.x+baseWidth, bottomLeft.y)
      );
  }

  @Override
  public String getType() {
    return Parallelogram.TYPE;
  }
}
