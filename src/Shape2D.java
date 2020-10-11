import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Shape2D implements Serializable, Drawable, Rotatable, Translatable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int rotationAngle;
  private double area;
  private double perimeter;

  public Shape2D() {
    this.rotationAngle = 0;
  }

  public static boolean isValidShape2D(Shape2D shapeToCheck) {
    if (shapeToCheck == null) {
      return false;
    }
    shapeToCheck.updateArea();
    shapeToCheck.updatePerimeter();
    return (shapeToCheck.getArea() > 0) && (shapeToCheck.getPerimeter() > 0);
  }

  public abstract String getType();

  public abstract double calculateArea();

  public abstract double calculatePerimeter();
  
  public abstract void translate(int dx, int dy);

  public abstract void draw(Graphics2D g2d);

  @Override
  public String toString() {
    return String.format(
      "type: %s\tarea: %.2f\tperimeter: %.2f",
      this.getType(),
      this.getArea(),
      this.getPerimeter()
      );
  }

  public void updateArea() {
    this.area = this.calculateArea();
  }

  public void updatePerimeter() {
    this.perimeter = this.calculatePerimeter();
  }

  public double getArea() {
    return this.area;
  }

  public double getPerimeter() {
    return this.perimeter;
  }

  public int getRotationAngle() {
    return this.rotationAngle;
  }

  public void setRotationAngle(int angle) {
    this.rotationAngle = angle;
    this.validateRotationAngle();
  }

  public void rotateClockwise(int angle) {
    this.rotationAngle += angle;
    this.validateRotationAngle();
  }
  
  private void validateRotationAngle() {
    if (this.rotationAngle >= 360) {
      this.rotationAngle = this.rotationAngle % 360;
    }
  }
}
