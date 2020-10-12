import java.awt.Graphics2D;

/**
 * This interface imposes a method to draw
 * on a provided {@code Graphics2D} object
 * on the objects of each class that implements it.
 * <p> created <b>2020-10-06</b>
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 * @see java.awt.Graphics2D
 */

public interface Drawable {
  
  /**
   * [draw]
   * Draws using a given {@code Graphics2D} object.
   * @param g2d The {@code Graphics2D} object used for drawing.
   * @see java.awt.Graphics2D
   */
  public void draw(Graphics2D g2d);
}