/**
 * This interface imposes a method 
 * on the objects of each class that implements it
 * to translate themselves horizontally and vertically.
 * <p> created <b>2020.10.05</b>
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public interface Translatable {
  /**
   * Translates by the given x and y values.
   * @param dx The horizontal translation value.
   * @param dy The vertical translation value.
   */
  public void translate(int dx, int dy);
}
