package src.main.java;

import java.awt.Point;

public class Rhombus extends Parallelogram {
  private static final String TYPE = "rhombus";

  public Rhombus(Point topLeft, Point bottomLeft) {
    super(
      topLeft,
      bottomLeft,
      (int)(Math.round(Math.hypot(topLeft.x-bottomLeft.x, topLeft.y-bottomLeft.y)))
    );
  }

  @Override
  public String getType() {
    return Rhombus.TYPE;
  }
}
