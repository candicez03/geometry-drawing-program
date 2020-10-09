package src.main.java;

import java.awt.Point;

public class Trapezoid extends Quadrilateral {
  private static final String TYPE = "trapezoid";

  public Trapezoid(Point p1, Point p2, Point p3, Point p4) {
    super(p1, p2, p3, p4);
  }

  @Override
  public String getType() {
    return Trapezoid.TYPE;
  }
}