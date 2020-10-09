package src.main.java;

public class Circle extends Ellipse {
  private static final String TYPE = "circle";

  public Circle(int x, int y, int diameter) {
    super(x, y, diameter, diameter);
  }

  @Override
  public String getType() {
    return Circle.TYPE;
  }
  
}
