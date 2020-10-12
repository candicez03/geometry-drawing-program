import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.ArrayList;

/** 
 * Draws a list of 2d shapes onto a screen.
 * <p> created <b>2020.10.06</b>
 * @version 1.0
 * @author Candice Zhang
 */

public class GeometryScreen {
  /** The frame used for drawing. */
  private JFrame frame;
  /** The Shape2D list used for drawing. */
  private ArrayList<Shape2D> shapes;

  /** 
   * Constructs a GeometryScreen with given width, height,
   * and a list of Shape2D objects.
   * @param width    The width of the screen.
   * @param height   The height of the screen.
   * @param shapes   The Shape2D list used for drawing.
   */
  public GeometryScreen(int width, int height, ArrayList<Shape2D> shapes) {
    this.frame = new JFrame("Geometry Drawing Program 1.0");
    
    JPanel graphicsPanel = new GraphicsPanel();
    
    this.frame.getContentPane().add(graphicsPanel);
    
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setSize(width, height);
    this.frame.setResizable(false);
    this.frame.setUndecorated(false);
    this.frame.setVisible(true);    
  } 
  
  /** 
   * Refreshes the screen by repainting the frame.
   */
  public void update() {
    this.frame.repaint();
  }

  /** 
   * Disposes all of this screen's frames and panels used for drawing.
   */
  public void dispose() {
    this.frame.dispose();
  }

  /** 
   * An inner class which contains all the details about drawing to screen.
   * <p> created <b>2020.10.06</b>
   * @version 1.0
   * @author Candice Zhang
   */
  @SuppressWarnings("serial")
  public class GraphicsPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      int xOffset = this.getWidth()/2;
      int yOffset = this.getHeight()/2;

      setDoubleBuffered(true);
      g.setColor(Color.BLACK);

      this.drawAxis(g);
      
      if (!shapes.isEmpty()) {
        g.translate(xOffset, yOffset);
        for(int i = 0; i < shapes.size(); i++) {
          shapes.get(i).draw((Graphics2D)g);
        }
        g.translate(-xOffset, yOffset); // reset translations
      }
    }

    /** 
     * Draws the x and y axis onto the screen,
     * using a given {@code Graphics} object.
     * @param g The {@code Graphics} context in which to paint.
     */
    public void drawAxis(Graphics g) {
      int width = this.getWidth();
      int height = this.getHeight();
      g.drawLine(width/2, 0, width/2, height); // y-axis
      g.drawLine(0, height/2, width, height/2); // x-axis
    }
  }
}
