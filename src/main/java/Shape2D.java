package src.main.java;

import java.awt.Graphics;

public abstract class Shape2D implements Drawable, Rotatable, Translatable {
  private int rotationAngle;
  private double area;
  private double perimeter;

  public Shape2D() {
    this.rotationAngle = 0;
  }

  public abstract String getType();

  public abstract double calculateArea();

  public abstract double calculatePerimeter();
  
  public abstract void translateX(int value);

  public abstract void translateY(int value);

  public abstract void draw(Graphics g);

  public void updateArea() {
    this.area = this.calculateArea();
  }

  public void updatePerimeter() {
    this.perimeter = this.getPerimeter();
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

  private void validateRotationAngle() {
    if (this.rotationAngle >= 360) {
      this.rotationAngle = this.rotationAngle % 360;
    }
  }
}
