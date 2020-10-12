import java.awt.Graphics2D;

/**
 * This interface imposes a method to draw
 * on a provided {@code Graphics2D} object
 * on the objects of each class that implements it.
 * <p> created <b>2020.10.05</b>
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public interface Drawable {
  
  /**
   * Draws using a given {@code Graphics2D} object.
   * @param g2d The {@code Graphics2D} object used for drawing.
   */
  public void draw(Graphics2D g2d);
}