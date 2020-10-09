package src.main.java;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Ellipse extends Shape2D {
  private static final String TYPE = "ellipse";

  private int x;
  private int y;
  private int width;
  private int height;

  public Ellipse(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public String getType() {
    return Ellipse.TYPE;
  }

  @Override
  public double calculatePerimeter() {
    //Ramanujan's approximation
    double a = this.width/2.0;
    double b = this.height/2.0;
    return Math.PI * ( 3*(a+b) - Math.sqrt( (3*a + b) * (a + 3*b) ) );
  }

  @Override
  public double calculateArea() {
    double a = this.width/2.0;
    double b = this.height/2.0;
    return Math.PI * a * b;
  }

  @Override
  public void translateX(int value) {
    this.x += value;
  }

  @Override
  public void translateY(int value) {
    this.y += value;
  }

  @Override
  public void draw(Graphics g) {
    if (this.getRotationAngle() == 0) {
      g.drawOval(this.x, this.y, this.width, this.height);
      return;
    }

    Graphics2D g2d = (Graphics2D)g;
    AffineTransform transformCopy = g2d.getTransform();
    g2d.rotate(Math.toRadians(this.getRotationAngle()));
    g.drawOval(this.x, this.y, this.width, this.height);
    g2d.setTransform(transformCopy);
  }
  
  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }
}
