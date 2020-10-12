import java.io.Serializable;

import java.awt.Graphics2D;

/**
 * The base class for 2D shapes.
 * <p> A 2D shape is a shape with two dimensions and,
 * thus, has an area and a perimeter.
 * <p> created <b>2020-10-05</b>
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public abstract class Shape2D implements Serializable, Drawable, Rotatable, Translatable {
  /** The serial version ID used for serialization. */
  private static final long serialVersionUID = 1L;

  /** The rotation angle of this Shape, in degrees, clockwise. */
  private int rotationAngle;
  /** The area of this Shape. */
  private double area;
  /** The perimeter of this Shape. */
  private double perimeter;

  /**
   * Constructs a new {@code Shape2D}.
   * <p>
   * Sets the shapes' rotation angle
   * to the default value (0 degrees).
   */
  public Shape2D() {
    this.rotationAngle = 0;
  }
  
  /** 
   * Checks if the given shape is valid.
   * <p>
   * A {@code Shape2D} is valid if its area and perimeter are both positive.
   * @param shapeToCheck  The {@code Shape2D} to be checked.
   * @return boolean, {@code true} if the {@code Shape2D} is valid;
   * {@code false} otherwise.
   */
  public static boolean isValidShape(Shape2D shapeToCheck) {
    if (shapeToCheck == null) {
      return false;
    }
    shapeToCheck.updateArea();
    shapeToCheck.updatePerimeter();
    return (shapeToCheck.getArea() > 0) && (shapeToCheck.getPerimeter() > 0);
  }

  
  /** 
   * Returns the specific type of the {@code Shape2D} in a {@code String}.
   * @return String, the type of the {@code Shape2D}.
   */
  public abstract String getType();

  
  /** 
   * Calculates and returns the area of the shape.
   * @return double, the area of the shape.
   */
  public abstract double calculateArea();

  
  /** 
   * Calculates and returns the perimeter of the shape.
   * @return double, the perimeter of the shape.
   */
  public abstract double calculatePerimeter();
  
  
  /** 
   * Translates the shape by given values of translation
   * along the x and y axis.
   * @param dx  The amount of horizontal translation.
   * @param dy  The amount of vertical translation.
   */
  public abstract void translate(int dx, int dy);

  
  /** 
   * Draws the shape.
   * @param g2d  The {@code Graphics2D} context in which to paint.
   */
  public abstract void draw(Graphics2D g2d);

  
  /** 
   * Returns a {@code String} representation of the {@code Shape2D}.
   * <p>
   * Includes: type, area, perimeter, rotation angle
   * @return String, a representation of the {@code Shape2D}.
   */
  @Override
  public String toString() {
    return String.format(
      "type: %s\tarea: %.2f\tperimeter: %.2f\trotation angle: %d",
      this.getType(),
      this.getArea(),
      this.getPerimeter(),
      this.rotationAngle
      );
  }

  /** 
   * Re-calculates the area of the {@code Shape2D} and set it as the area.
   * @return String
   */
  public void updateArea() {
    this.area = this.calculateArea();
  }

  /** 
   * Re-calculates the perimeter of the {@code Shape2D} and set it as the perimeter.
   * @return String
   */
  public void updatePerimeter() {
    this.perimeter = this.calculatePerimeter();
  }

  /** 
   * Rotates the {@code Shape2D} with the given angle, clockwise.
   * @param angle
   */
  public void rotateClockwise(int angle) {
    this.rotationAngle += angle;
    this.validateRotationAngle();
  }

  /** 
   * Returns the area of the {@code Shape2D}.
   * @return double, the area of the {@code Shape2D}.
   */
  public double getArea() {
    return this.area;
  }

  
  /** 
   * Returns the perimeter of the {@code Shape2D}.
   * @return double, the perimeter of the {@code Shape2D}.
   */
  public double getPerimeter() {
    return this.perimeter;
  }

  
  /** 
   * Returns the rotation angle of the {@code Shape2D}.
   * @return int, the rotation angle of the {@code Shape2D}.
   */
  public int getRotationAngle() {
    return this.rotationAngle;
  }

  
  /** 
   * Sets the the rotation angle of the {@code Shape2D}
   * to a given value. Values <0 or >360 will be normalized.
   * @param angle The new rotation angle of the {@code Shape2D}.
   */
  public void setRotationAngle(int angle) {
    this.rotationAngle = angle;
    this.validateRotationAngle();
  }
  
  /** 
   * Validates the rotation angle of the {@code Shape2D}.
   * <p>
   * Values <0 or >360 will be normalized.
   * @param angle The new rotation angle of the {@code Shape2D}.
   */
  private void validateRotationAngle() {
    if (this.rotationAngle >= 360) {
      this.rotationAngle = this.rotationAngle % 360;
    }
  }
  
}
