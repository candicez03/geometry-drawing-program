package src.main.java;

public class Square extends Rectangle {
  private static final String TYPE = "square";

  public Square(int x, int y, int sideLength) {
    super(x, y, sideLength, sideLength);
  }

  @Override
  public String getType() {
    return Square.TYPE;
  }
}
