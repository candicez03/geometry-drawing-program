import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * An interactive program for drawing 2d geometry shapes.
 * <p>
 * This program interacts with the user in the console and
 * draws 2d shapes on a separate screen.
 * <p> created <b>2020.10.04</b>
 * @version 1.0
 * @author Candice Zhang
 */

class GeometryDrawingProgram {

  /** The file extension for saving. */
  public static final String FILE_EXTENSION = ".geo";
  /** The path to the folder for saving. */
  public static final String SAVING_FOLDER  = "saves/";
  /** A string that contains all the characters that are permitted to use in file names.*/
  public static final String LEGAL_FILENAME_CHARS =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-.";

  /** An array of all available commands.*/
  public static final String[] COMMANDS = new String[] {
    "Display all Shapes to console",
    "Add a single Shape",
    "Remove a single Shape",
    "Translate a single Shape",
    "Rotate a single Shape",
    "Translate entire drawing",
    "Rotate entire drawing",
    "Save drawing to a file",
    "Load Drawing from a file",
    "Quit"
  };

  /** An array of all constructible shapes.*/
  public static final String[] CONSTRUCTIBLE_SHAPES = new String[] {
    "ellipse",
    "circle",
    "simple polygon",
    "triangle",
    "quadrilateral",
    "parallelogram",
    "trapezoid",
    "rectangle",
    "rhombus",
    "square"
  };

  /** An array of input prompts for an Ellipse object.*/
  public static final String[] PROMPTS_ELLIPSE = new String[] {
    "x (top-left)",
    "y (top-left)",
    "width",
    "height"
  };
  
  /** An array of input prompts for a Circle object.*/
  public static final String[] PROMPTS_CIRCLE = new String[] {
    "x (top-left)",
    "y (top-left)",
    "diameter",
  };

  /** An array of input prompts for a Parallelogram object.*/
  public static final String[] PROMPTS_PARALLELOGRAM = new String[] {
    "x (top-left)",
    "y (top-left)",
    "x (bottom-left)",
    "y (bottom-left)",
    "base width"
  };

  /** An array of input prompts for a Rectangle object.*/
  public static final String[] PROMPTS_RECTANGLE = new String[] {
    "x (top-left)",
    "y (top-left)",
    "width",
    "height"
  };

  /** An array of input prompts for a Rhombus object.*/
  public static final String[] PROMPTS_RHOMBUS = new String[] {
    "x (top-left)",
    "y (top-left)",
    "x (bottom-left)",
    "y (bottom-left)"
  };

  /** An array of input prompts for a Square object.*/
  public static final String[] PROMPTS_SQUARE = new String[] {
    "x (top-left)",
    "y (top-left)",
    "side length"
  };

  /** An array of input prompts for a Trapezoid object.*/
  public static final String[] PROMPTS_TRAPEZOID = new String[] {
    "x (top-left)",
    "y (top-right)",
    "x (bottom-left)",
    "y (bottom-right)",
    "top width",
    "base width"
  };

  /** The frame used for geometry drawing. */
  public static JFrame frame = new JFrame();

  /** The list of 2d shapes to be drawn. */
  public static ArrayList<Shape2D> shapes;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    shapes = new ArrayList<Shape2D>();
    String inputPromptMsg = "Please enter an integer to select one of the options from the menu.";
    int commandIdx = -1;
    
    System.out.println("Welcome to Geometry Drawing Program 1.0!");

    GeometryScreen geoScreen = new GeometryScreen(600, 600);

    do {
      frame.repaint(); //update the screen

      System.out.println("\n" + inputPromptMsg);
      printNumberedStrings(COMMANDS);
      
      commandIdx = getIntInRangeFromInput(input, 1, COMMANDS.length) - 1;
      handleCommand(input, commandIdx);

    } while(commandIdx != COMMANDS.length-1);

    frame.dispose();
    input.close();
  }
  
  
  /** 
   * Handles a command according to the given command index.
   * @param input       The Scanner that will be used to take input.
   * @param commandIdx  A command index referring to
   *                    the corresponding command in COMMANDS.
   */
  @SuppressWarnings("unchecked")
  public static void handleCommand(Scanner input, int commandIdx) {
    switch(commandIdx) {
    // Display all Shapes to console
    case 0:
      printShapesInfo();
      break;

    // Add a single Shape
    case 1:
      try {
        Shape2D newShape = generateShape2DFromInput(input);
        shapes.add(newShape);
      } catch (InvalidShapeException e) {
        System.out.println(e.getMessage());
      }
      break;

    // Remove a single Shape
    case 2:
      printShapesInfo();
      System.out.print("Enter the index of the shape you would like to remove: ");
      int idxToRemove = getIntInRangeFromInput(input, 1, shapes.size()) - 1;
      shapes.remove(idxToRemove);
      break;

    // Translate a single Shape
    case 3:
      if (shapes.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        printShapesInfo();
        System.out.print("Enter the index of the shape you would like to translate: ");
        int idxToTranslate = getIntInRangeFromInput(input, 1, shapes.size()) - 1;
        System.out.print("x value of translation: ");
        int x = getIntFromInput(input);
        System.out.print("y value of translation: ");
        int y = getIntFromInput(input);

        shapes.get(idxToTranslate).translate(x, y);
      }
      break;
    
    // Rotate a single Shape
    case 4:
      if (shapes.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        printShapesInfo();
        System.out.print("Enter the index of the shape you would like to rotate: ");
        int idxToRotate = getIntInRangeFromInput(input, 1, shapes.size()) - 1;
        System.out.print("Enter the degree of rotation, clockwise: ");
        int angle = getIntFromInput(input);

        shapes.get(idxToRotate).rotateClockwise(angle);
      }
      break;

    // Translate entire drawing
    case 5:
      if (shapes.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        System.out.print("x value of translation: ");
        int x = getIntFromInput(input);
        System.out.print("y value of translation: ");
        int y = getIntFromInput(input);

        for (Shape2D shape: shapes) {
          shape.translate(x, y);
        }
      }
      break;

    // Rotate entire drawing
    case 6:
      if (shapes.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        System.out.print("Enter the degree of rotation, clockwise: ");
        int angle = getIntFromInput(input);
        for (Shape2D shape: shapes) {
          shape.rotateClockwise(angle);
        }
      }
      break;

    // Save drawing to a file
    case 7:
      System.out.print("Enter the name of the file: ");
      String fileInName = input.nextLine();
      if (!isLegalFileName(fileInName)) {
        System.out.println("File name contains illegal characters.");
        break;
      }

      String fileInPath = SAVING_FOLDER + fileInName + FILE_EXTENSION;

      if(Files.exists(Paths.get(fileInPath))) { 
        System.out.print("This file already exists. Enter 'y' to overwrite: ");
        String choice = input.nextLine();
        if (!choice.equals("y")) {
          System.out.println("Saving aborted");
          break;
        }
      }
      
      try {
        FileOutputStream file = new FileOutputStream(fileInPath);
        ObjectOutputStream outputStream = new ObjectOutputStream(file);
        outputStream.writeObject(shapes);
        outputStream.close();
        file.close();
        System.out.println("Successfully saved");
      
      } catch (IOException e) {
        e.printStackTrace();
      }
      break;
    
    // Load Drawing from a file
    case 8:
      System.out.print("Enter the name of the file: ");
      String fileOutName = input.nextLine();
      if (!isLegalFileName(fileOutName)) {
        System.out.println("File name contains illegal characters.");
        break;
      }

      String fileOutPath = SAVING_FOLDER + fileOutName + FILE_EXTENSION;
      
      if(Files.notExists(Paths.get(fileOutPath))) { 
        System.out.println("File does not exist!");
        break;
      }

      try {
        FileInputStream file = new FileInputStream(fileOutPath);
        ObjectInputStream inputStream = new ObjectInputStream(file);
        ArrayList<Shape2D> newShapes = (ArrayList<Shape2D>)(inputStream.readObject());
        shapes = newShapes;
        inputStream.close();
        file.close();
        System.out.println("Successfully loaded");

      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      break;
    
    // Quit
    case 9:
      System.out.println("Bye!");
      break;
    }    
  }

  
  /** 
   * Asks the user for relevant data to generate
   * a specific type of Shape2D object.
   * @param input The Scanner that will be used to take input.
   * @return      Shape2D, the generated Shape2D object using user input.
   * @throws InvalidShapeException  Thrown when the {@code Shape2D} created
   *                                has an area or perimeter of 0.
   */
  public static Shape2D generateShape2DFromInput(Scanner input)
    throws InvalidShapeException {

    Shape2D shape2DToReturn = null;

    System.out.println("Please select one of the shapes to generate:");
    printNumberedStrings(CONSTRUCTIBLE_SHAPES);
    
    int shapeTypeIdx = getIntInRangeFromInput(input, 1, CONSTRUCTIBLE_SHAPES.length) - 1;
    
    String[] prompts;
    switch(shapeTypeIdx) {
    // ellipse
    case 0:
      prompts = PROMPTS_ELLIPSE;
      int[] ellipseArgs = new int[prompts.length];
      for (int i = 0; i < prompts.length; i++) {
        System.out.print(prompts[i] + ": ");
        ellipseArgs[i] = getIntFromInput(input);
      }
      shape2DToReturn = new Ellipse(
        new Point(ellipseArgs[0], ellipseArgs[1]),
        ellipseArgs[2],
        ellipseArgs[3]
      );
      break;

    // circle
    case 1:
      prompts = PROMPTS_CIRCLE;
      int[] circleArgs = new int[prompts.length];
      for (int i = 0; i < prompts.length; i++) {
        System.out.print(prompts[i] + ": ");
        circleArgs[i] = getIntFromInput(input);
      }
      shape2DToReturn = new Circle(
        new Point(circleArgs[0], circleArgs[1]),
        circleArgs[2]
      );
      break;

    // simple polygon
    case 2:
      System.out.print("number of points: ");
      int nPoints = getIntInRangeFromInput(input, 3, Integer.MAX_VALUE);
      Point[] simplePolygonArgs = new Point[nPoints];

      for (int i = 0; i < nPoints; i++) {
        System.out.print("x of point " + Integer.toString(i+1) + ": ");
        int x = getIntFromInput(input);
        System.out.print("y of point " + Integer.toString(i+1) + ": ");
        int y = getIntFromInput(input);

        simplePolygonArgs[i] = new Point(x, y);
      }
      shape2DToReturn = new SimplePolygon(simplePolygonArgs);
      break;

    // triangle
    case 3:
      Point[] triangleArgs = new Point[3];

      for (int i = 0; i < 3; i++) {
        System.out.print("x of point " + Integer.toString(i+1) + ": ");
        int x = getIntFromInput(input);
        System.out.print("y of point " + Integer.toString(i+1) + ": ");
        int y = getIntFromInput(input);

        triangleArgs[i] = new Point(x, y);
      }
      shape2DToReturn = new Triangle(
        triangleArgs[0],
        triangleArgs[1],
        triangleArgs[2]
      );
      break;

    // quadrilateral
    case 4:
      Point[] quadrilateralArgs = new Point[4];

      for (int i = 0; i < 4; i++) {
        System.out.print("x of point " + Integer.toString(i+1) + ": ");
        int x = getIntFromInput(input);
        System.out.print("y of point " + Integer.toString(i+1) + ": ");
        int y = getIntFromInput(input);

        quadrilateralArgs[i] = new Point(x, y);
      }
      shape2DToReturn = new Quadrilateral(
        quadrilateralArgs[0],
        quadrilateralArgs[1],
        quadrilateralArgs[2],
        quadrilateralArgs[3]
      );
      break;

    // parallelogram
    case 5:
      prompts = PROMPTS_PARALLELOGRAM;
      int[] parallelogramArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print(prompts[i] + ": ");
        parallelogramArgs[i] = getIntFromInput(input);
      }
      shape2DToReturn = new Parallelogram(
        new Point (parallelogramArgs[0], parallelogramArgs[1]),
        new Point (parallelogramArgs[2], parallelogramArgs[3]),
        parallelogramArgs[4]
      );

      break;

    // trapezoid
    case 6:
      prompts = PROMPTS_TRAPEZOID;
      int[] trapezoidArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print(prompts[i] + ": ");
        trapezoidArgs[i] = getIntFromInput(input);
      }
      shape2DToReturn = new Trapezoid(
        new Point (trapezoidArgs[0], trapezoidArgs[1]),
        new Point (trapezoidArgs[2], trapezoidArgs[3]),
        trapezoidArgs[4],
        trapezoidArgs[5]
      );
      break;

    // rectangle
    case 7:
      prompts = PROMPTS_RECTANGLE;
      int[] rectangleArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print(prompts[i] + ": ");
        rectangleArgs[i] = getIntFromInput(input);
      }

      shape2DToReturn = new Rectangle(
        new Point(rectangleArgs[0], rectangleArgs[1]),
        rectangleArgs[2],
        rectangleArgs[3]
      );
      break;

    // rhombus
    case 8:
      prompts = PROMPTS_RHOMBUS;
      int[] rhombusArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print(prompts[i] + ": ");
        rhombusArgs[i] = getIntFromInput(input);
      }
      shape2DToReturn = new Rhombus(
        new Point(rhombusArgs[0], rhombusArgs[1]),
        new Point(rhombusArgs[2], rhombusArgs[3])
      );
      break;

    // square
    case 9:
      prompts = PROMPTS_SQUARE;
      int[] squareArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print(prompts[i] + ": ");
        squareArgs[i] = getIntFromInput(input);
      }
      shape2DToReturn = new Square(
        new Point(squareArgs[0], squareArgs[1]),
        squareArgs[2]
      );
      break;
    }

    if (!Shape2D.isValidShape(shape2DToReturn)) {
      throw new InvalidShapeException(
        "Invalid shape. The area/perimeter of the constructed shape is not positive."
      );
    }
    return shape2DToReturn;
  }

  
  /** 
   * Asks the user for an integer in a specified range
   * and returns the integer.
   * @param input   The Scanner that will be used to take input.
   * @param min     The minimum accepted value for the integer.
   * @param max     The maximum accepted value for the integer.
   * @return        int, an integer taken from the input that is
   *                within the given range.
   */
  public static int getIntInRangeFromInput(Scanner input, int min, int max) {
    int userInt = getIntFromInput(input);
    while (!isInRange(userInt, min, max)) {
      System.out.printf(
        "Integer not in range(%d-%d). Please enter again: ",
        min,
        max);
      userInt = getIntFromInput(input);
    }
    return userInt;
  }

  
  /** 
   * Asks the user for an integer and returns the integer.
   * @param input   The Scanner that will be used to take input.
   * @return        int, an integer taken from the input.
   */
  public static int getIntFromInput(Scanner input) {
    String userInput = input.nextLine();
    while (!isInteger(userInput)) {
      System.out.print("Expected an integer. Please enter again: ");
      userInput = input.nextLine();
    }
    return Integer.valueOf(userInput);
  }

  /** 
   * Prints the information of each of the shapes
   * in the static shapes array.
   * <p>
   * Format: "1. abc\n2. abc..."
   */
  public static void printShapesInfo() {
    if (shapes.isEmpty()) {
      System.out.println("There are currently no shapes in this drawing.");
    } else {
      String[] shapeStrings = new String[shapes.size()];
      for (int i = 0; i < shapes.size(); i++) {
        shapeStrings[i] = shapes.toString();
      }
      System.out.println("Displaying info of all shapes...");
      printNumberedStrings(shapeStrings);
    }
  }

  /** 
   * Numbers and prints an array of Strings.
   * <p>
   * Format: "1. abc\n2. abc..."
   * @param strings  The array of strings to be numbered.
   */
  public static void printNumberedStrings(String[] strings) {
    for (int i = 0; i < strings.length; i++) {
      System.out.println(Integer.toString(i+1) + ". " + strings[i]);
    }
  }

  /** 
   * Checks if the given file name only contains
   * legal/permitted characters.
   * <p>
   * Permitted characters:
   * "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-."
   * @param fileName  The file name to be checked.
   * @return boolean, true if the given file name only contains
   *         legal/permitted characters; false otherwise.
   */
  public static boolean isLegalFileName(String fileName) {
    for (int i = 0; i < fileName.length(); i++) {
      String cur = fileName.substring(i, i+1);
      if (LEGAL_FILENAME_CHARS.indexOf(cur) == -1) {
        return false;
      }
    }
    return true;
  }

  /** 
   * Checks if the given string only contains
   * an integer.
   * @param str  The string to be checked.
   * @return boolean, true if the given string only contains
   *         an integer; false otherwise.
   */
  public static boolean isInteger(String str) {
    if (str == null) {
      return false;
    }

    int strLength = str.length();
    if (strLength == 0) {
      return false;
    }

    int i = 0;
    if (str.charAt(0) == '-') {
      if (strLength == 1) {
        return false;
      }
      i = 1;
    }

    for (; i < strLength; i++) {
      char c = str.charAt(i);
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }

  
  /** 
   * Checks if the given integer is within a specified range.
   * <p>
   * Range bounds are inclusive.
   * @param number  The number to be checked.
   * @param min     The minimum value of the range.
   * @param max     The maximum value of the range.
   * @return        boolean, true if the given integer is
   *                within a specified range; false otherwise.
   */
  public static boolean isInRange(int number, int min, int max) {
    return (number >= min) && (number <= max);
  }

  /** 
   * Draws a list of 2d shapes onto a screen.
   * <p> created <b>2020.10.06</b>
   * @version 1.0
   * @author Candice Zhang
   */
  public static class GeometryScreen {
    /** 
     * Constructs a GeometryScreen with given width, height,
     * and a list of Shape2D objects.
     * @param width    The width of the screen.
     * @param height   The height of the screen.
     */
    public GeometryScreen(int width, int height) {
      frame = new JFrame("Geometry Drawing Program 1.0");
      
      JPanel graphicsPanel = new GraphicsPanel();
      
      frame.getContentPane().add(graphicsPanel);
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(width, height);
      frame.setResizable(false);
      frame.setUndecorated(false);
      frame.setVisible(true);    
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

        this.drawCartesianPlane(g);
        
        if (!shapes.isEmpty()) {
          g.translate(xOffset, yOffset);
          for(int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw((Graphics2D)g);
          }
          g.translate(-xOffset, yOffset); // reset translations
        }
      }

      /** 
       * Draws the x and y axis and the cartesian plane grid
       * onto the screen, using a given {@code Graphics} object.
       * @param g The {@code Graphics} context in which to paint.
       */
      public void drawCartesianPlane(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();

        // white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // grid lines
        g.setColor(new Color(225, 225, 225));
        int gridSize = 10;
        
        int wGrids = width/2/gridSize;
        for(int i = -wGrids; i < wGrids+1; i++) {
          g.drawLine(width/2 + i*gridSize, 0, width/2 + i*gridSize, height);
        }

        int hGrids = height/2/gridSize;
        for(int i = -hGrids; i < hGrids+1; i++) {
          g.drawLine(0, height/2 + i*gridSize, width, height/2 + i*gridSize);
        }

        // axis
        g.setColor(Color.BLACK);
        g.drawLine(width/2, 0, width/2, height); // y-axis
        g.drawLine(0, height/2, width, height/2); // x-axis

        
      }
    }
  }
}