/**
 * This interface imposes methods to
 * set/return a rotation angle
 * on the objects of each class that implements it.
 * <p>
 * The angle is always expressed in degrees, clockwise.
 * <p> created <b>2020.10.05</b>
 * @since 1.0
 * @version 1.0
 * @author Candice Zhang
 */

public interface Rotatable {

  /**
   * Returns the rotation angle, in degrees.
   * @return int, the rotation angle, in degrees.
   */
  public int getRotationAngle();
  
  /**
   * Sets the rotation angle to a given integer.
   * @param angle The new rotation angle, in degrees.
   */
  public void setRotationAngle(int angle);

  /**
   * Increases the rotation angle by a given integer value.
   * @param angle The angle to be added, in degrees.
   */
  public void rotateClockwise(int angle);
}
