import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;

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
 * GeometryDrawingProgram
 * @version 1.0
 * @author Candice Zhang
 * 2020.10.04
 */

class GeometryDrawingProgram {
  public static JFrame frame;
  public static ArrayList<Shape2D> shapes;
  
  public static final String FILE_EXTENSION = ".geo";
  public static final String SAVING_FOLDER  = "saves/";
  public static final String LEGAL_FILENAME_CHARS =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-.";

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

  public static final String[] PROMPTS_ELLIPSE = new String[] {
    "x (top-left)",
    "y (top-left)",
    "width",
    "height"
  };
  
  public static final String[] PROMPTS_CIRCLE = new String[] {
    "x (top-left)",
    "y (top-left)",
    "diameter",
  };

  public static final String[] PROMPTS_PARALLELOGRAM = new String[] {
    "x (top-left)",
    "y (top-left)",
    "x (bottom-left)",
    "y (bottom-left)",
    "base width"
  };

  public static final String[] PROMPTS_RECTANGLE = new String[] {
    "x (top-left)",
    "y (top-left)",
    "width",
    "height"
  };

  public static final String[] PROMPTS_RHOMBUS = new String[] {
    "x (top-left)",
    "y (top-left)",
    "x (bottom-left)",
    "y (bottom-left)"
  };

  public static final String[] PROMPTS_SQUARE = new String[] {
    "x (top-left)",
    "y (top-left)",
    "side length"
  };

  
  public static final String[] PROMPTS_TRAPEZOID = new String[] {
    "x (top-left)",
    "y (top-right)",
    "x (bottom-left)",
    "y (bottom-right)",
    "top width",
    "base width"
  };

  
  /** 
   * @param args
   */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    shapes = new ArrayList<Shape2D>();
    String menu_options = toNumberedString(COMMANDS);
    String inputPromptMsg = "Please enter an integer to select one of the options from the menu.";
    int commandIdx = -1;
    
    System.out.println("Welcome to Geometry Drawing Program 1.0!");

    GeometryScreen geoScreen = new GeometryScreen(600, 600);

    do {
      frame.repaint(); //update the screen

      System.out.println("\n" + inputPromptMsg);
      System.out.println(menu_options);
      
      commandIdx = getIntInRangeFromInput(input, 1, COMMANDS.length) - 1;
      handleCommand(input, commandIdx);

    } while(commandIdx != COMMANDS.length-1);

    frame.dispose();
    input.close();
  }
  
  
  /** 
   * @param input
   * @param commandIdx
   */
  public static void handleCommand(Scanner input, int commandIdx) {
    switch(commandIdx) {
    // Display all Shapes to console
    case 0:
      if (shapes.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");
      } else {
        for (int i = 0; i < shapes.size(); i++) {
          System.out.printf(
            "%d) %s\n",
            i+1,
            shapes.get(i).toString());
        }
      }
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
      if (shapes.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        System.out.println("Displaying info of all shapes...");
        for (Shape2D shape: shapes) {
          System.out.println(shape.toString());
        }
        System.out.print("Enter the index of the shape you would like to remove: ");
        int idxToRemove = getIntInRangeFromInput(input, 1, shapes.size()) - 1;
        shapes.remove(idxToRemove);
      }
      break;

    // Translate a single Shape
    case 3:
      if (shapes.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        System.out.println("Displaying info of all shapes...");
        for (Shape2D shape: shapes) {
          System.out.println(shape.toString());
        }

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
        System.out.println("Displaying info of all shapes...");
        for (Shape2D shape: shapes) {
          System.out.println(shape.toString());
        }

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
      
      } catch (InvalidPathException e) {
        System.out.println("e.getReason()");
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
   * @param input
   * @return Shape2D
   * @throws InvalidShapeException
   */
  public static Shape2D generateShape2DFromInput(Scanner input)
    throws InvalidShapeException {

    Shape2D shape2DToReturn = null;
    String shape2DOptionsMsg = toNumberedString(CONSTRUCTIBLE_SHAPES);

    System.out.println("Please select one of the shapes to generate:");
    System.out.println(shape2DOptionsMsg);
    
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
      try {
        shape2DToReturn = new SimplePolygon(simplePolygonArgs);
      } catch (IllegalArgumentException e) {
        throw new InvalidShapeException(
          "The given points do not form a simple polygon.",
          shape2DToReturn
        );
      }
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
        "Invalid shape. The area/perimeter of the constructed shape is 0.",
        shape2DToReturn
      );
    }
    return shape2DToReturn;
  }

  
  /** 
   * @param input
   * @param min
   * @param max
   * @return int
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
   * @param input
   * @return int
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
   * @param strings
   * @return String
   */
  public static String toNumberedString(String[] strings) {
    String result = "";
    for (int i = 0; i < strings.length; i++) {
      result += Integer.toString(i+1) + ". " + strings[i] + "\n";
    }
    return result.trim();
  }

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
   * @param str
   * @return boolean
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
   * @param number
   * @param min
   * @param max
   * @return boolean
   */
  public static boolean isInRange(int number, int min, int max) {
    return (number >= min) && (number <= max);
  }

  public static class GeometryScreen {
    GeometryScreen(int width, int height) {
      frame = new JFrame("Geometry Drawing Program 1.0");
      
      JPanel graphicsPanel = new GraphicsPanel();
      
      frame.getContentPane().add(graphicsPanel);
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(width, height);
      frame.setResizable(false);
      frame.setUndecorated(false);
      frame.setVisible(true);    
    } 
    
    @SuppressWarnings("serial")
  //This is an inner class which contains all the details about drawing to screen.
    public static class GraphicsPanel extends JPanel {
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

      public void drawAxis(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        g.drawLine(width/2, 0, width/2, height); // y-axis
        g.drawLine(0, height/2, width, height/2); // x-axis
      }
    }
  }
}