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
import java.nio.file.Paths;

/**
 * GeometryDrawingProgram
 * @version 1.0
 * @author Candice Zhang
 * 2020.10.04
 */

class GeometryDrawingProgram {
  public static JFrame frame;
  public static ArrayList<Shape2D> shape2Ds;
  
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

  public static final String[] CONSTRUCTIBLE_SHAPE2DS = new String[] {
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
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    shape2Ds = new ArrayList<Shape2D>();
    String menu_options = toNumberedString(COMMANDS);
    String inputPromptMsg = "Please enter an integer to select one of the options from the menu.";
    String userInput;
    int userChoice = -1;
    
    System.out.println("Welcome to Geometry Drawing Program 1.0!");

    GeometryScreen geoScreen = new GeometryScreen(600, 600);

    do {
      frame.repaint(); //update the screen

      System.out.println(inputPromptMsg);
      System.out.println(menu_options);
      
      userInput = input.nextLine();
      if (!isInteger(userInput)) {
        System.out.println("Invalid input! not a number. " + inputPromptMsg);
      } else {
        userChoice = Integer.valueOf(userInput) - 1;
        if (isInRange(userChoice, 0, COMMANDS.length-1)) {
          handleCommand(input, userChoice);
        } else {
          System.out.println("Invalid input! number not in range. " + inputPromptMsg);
        }
      }
    } while(userChoice != COMMANDS.length-1);

    frame.dispose();
    input.close();

    System.out.println("bye");
  }
  
  public static void handleCommand(Scanner input, int commandIdx) {
    switch(commandIdx) {
    // Display all Shapes to console
    case 0:
      if (shape2Ds.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");
      } else {
        for (int i = 0; i < shape2Ds.size(); i++) {
          System.out.printf(
            "%d) %s\n",
            i+1,
            shape2Ds.get(i).toString());
        }
      }
      break;

    // Add a single Shape
    case 1:
      try {
        Shape2D newShape2D = generateShape2DFromInput(input);
        shape2Ds.add(newShape2D);
      } catch (IllegalArgumentException e) {
        System.out.println("IllegalArgumentException");
      } catch (InvalidShapeException e) {
        System.out.println(e.getMessage());
      }
      break;

    // Remove a single Shape
    case 2:
      if (shape2Ds.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        System.out.println("Displaying info of all shapes...");
        for (Shape2D shape: shape2Ds) {
          System.out.println(shape.toString());
        }

        System.out.println("Enter the index of the shape you would like to remove: ");
        try {
          int idxToRemove = getIntInRangeFromInput(input, 1, shape2Ds.size()) - 1;
          shape2Ds.remove(idxToRemove);
        } catch (NumberFormatException e) {
          System.out.println("NumberFormatException");
        } catch (IntNotInRangeException e) {
          System.out.println("IntNotInRangeException");
        }        
      }
      break;

    // Translate a single Shape
    case 3:
      if (shape2Ds.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        System.out.println("Displaying info of all shapes...");
        for (Shape2D shape: shape2Ds) {
          System.out.println(shape.toString());
        }

        try {
          System.out.println("Enter the index of the shape you would like to translate: ");
          int idxToTranslate = getIntInRangeFromInput(input, 1, shape2Ds.size()) - 1;
          int x;
          int y;

          System.out.println("Enter the x value of translation");
          x = getIntInRangeFromInput(input, Integer.MIN_VALUE, Integer.MAX_VALUE);
          System.out.println("Enter the y value of translation");
          y = getIntInRangeFromInput(input, Integer.MIN_VALUE, Integer.MAX_VALUE);

          shape2Ds.get(idxToTranslate).translateX(x);
          shape2Ds.get(idxToTranslate).translateY(y);

        } catch (NumberFormatException e) {
          System.out.println("NumberFormatException");
        } catch (IntNotInRangeException e) {
          System.out.println("IntNotInRangeException");
        }        
      }
      break;
    
    // Rotate a single Shape
    case 4:
      if (shape2Ds.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        System.out.println("Displaying info of all shapes...");
        for (Shape2D shape: shape2Ds) {
          System.out.println(shape.toString());
        }

        try {
          System.out.println("Enter the index of the shape you would like to rotate: ");
          int idxToRotate = getIntInRangeFromInput(input, 1, shape2Ds.size()) - 1;
          int angle;
          System.out.println("Enter the degree of rotation, clockwise:");
          angle = getIntInRangeFromInput(input, Integer.MIN_VALUE, Integer.MAX_VALUE);

          shape2Ds.get(idxToRotate).rotateClockwise(angle);
          
        } catch (NumberFormatException e) {
          System.out.println("NumberFormatException");
        } catch (IntNotInRangeException e) {
          System.out.println("IntNotInRangeException");
        }        
      }
      break;

    // Translate entire drawing
    case 5:
      if (shape2Ds.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        try {
          int x;
          int y;
          System.out.println("Enter the x value of translation");
          x = getIntInRangeFromInput(input, Integer.MIN_VALUE, Integer.MAX_VALUE);
          System.out.println("Enter the y value of translation");
          y = getIntInRangeFromInput(input, Integer.MIN_VALUE, Integer.MAX_VALUE);

          for (Shape2D shape: shape2Ds) {
            shape.translateX(x);
            shape.translateY(y);
          }
          
        } catch (NumberFormatException e) {
          System.out.println("NumberFormatException");
        } catch (IntNotInRangeException e) {
          System.out.println("IntNotInRangeException");
        }        
      }
      break;

    // Rotate entire drawing
    case 6:
      if (shape2Ds.isEmpty()) {
        System.out.println("There are currently no shapes in this drawing.");

      } else {
        try {
          int angle;
          System.out.println("Enter the degree of rotation, clockwise:");
          angle = getIntInRangeFromInput(input, Integer.MIN_VALUE, Integer.MAX_VALUE);

          for (Shape2D shape: shape2Ds) {
            shape.rotateClockwise(angle);
          }
          
        } catch (NumberFormatException e) {
          System.out.println("NumberFormatException");
        } catch (IntNotInRangeException e) {
          System.out.println("IntNotInRangeException");
        }        
      }
      break;

    // Save drawing to a file
    case 7:
      System.out.println("Enter the name of the file");
      String fileInPath = input.nextLine();

      try {
        FileOutputStream file = new FileOutputStream(fileInPath);
        ObjectOutputStream outputStream = new ObjectOutputStream(file);
        outputStream.writeObject(shape2Ds);
        outputStream.close();
        file.close();
      } catch (IOException e) {
        System.out.println("IOException");
      }
      break;
    
    // Load Drawing from a file
    case 8:
      System.out.println("Enter the name of the file");
      String fileOutPath = input.nextLine();
      if(Files.notExists(Paths.get(fileOutPath))) { 
        System.out.println("File does not exist!");
        break;
      }

      try {
        FileInputStream file = new FileInputStream(fileOutPath);
        ObjectInputStream inputStream = new ObjectInputStream(file);
        ArrayList<Shape2D> newShape2Ds = (ArrayList<Shape2D>)(inputStream.readObject());
        shape2Ds = newShape2Ds;
        inputStream.close();
        file.close();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        System.out.println("ClassNotFoundException");
      }
      break;
    
    // Quit
    case 9:
      break;
    }    
  }

  public static Shape2D generateShape2DFromInput(Scanner input)
    throws IllegalArgumentException,
           InvalidShapeException {
    Shape2D shape2DToReturn = null;
    String shape2DOptionsMsg = toNumberedString(CONSTRUCTIBLE_SHAPE2DS);
    System.out.println("Please select one of the shapes to generate:");
    System.out.println(shape2DOptionsMsg);
    int shapeTypeIdx = -1;
    
    try {
      shapeTypeIdx = getIntInRangeFromInput(input, 1, CONSTRUCTIBLE_SHAPE2DS.length) - 1;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("", e);
    } catch (IntNotInRangeException e) {
      throw new IllegalArgumentException("", e);
    }
    
    String[] prompts;
    String line;
    switch(shapeTypeIdx) {
    // ellipse
    case 0:
      prompts = Ellipse.PROMPTS;
      int[] ellipseArgs = new int[prompts.length];
      for (int i = 0; i < prompts.length; i++) {
        System.out.print("Enter the " + prompts[i] + " of the ellipse: ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          ellipseArgs[i] = Integer.valueOf(line);
        }
      }
      try {
        shape2DToReturn = new Ellipse(
          ellipseArgs[0],
          ellipseArgs[1],
          ellipseArgs[2],
          ellipseArgs[3]
        );
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("", e);
      }
      break;

    // circle
    case 1:
      prompts = Circle.PROMPTS;
      int[] circleArgs = new int[prompts.length];
      for (int i = 0; i < prompts.length; i++) {
        System.out.print("Enter the " + prompts[i] + " of the circle: ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          circleArgs[i] = Integer.valueOf(line);
        }
      }
      try {
        shape2DToReturn = new Circle(
          circleArgs[0],
          circleArgs[1],
          circleArgs[2]
        );
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("", e);
      }
      break;

    // simple polygon
    case 2:
      System.out.print("Enter the number of points the simple polygon has: ");
      int nPoints;
      try {
        nPoints = getIntInRangeFromInput(input, 3, Integer.MAX_VALUE);
      } catch (NumberFormatException e) {
        throw e;
      } catch (IntNotInRangeException e) {
        throw new IllegalArgumentException("", e);
      }

      Point[] simplePolygonArgs = new Point[nPoints];
      for (int i = 0; i < nPoints; i++) {
        int x, y;
        System.out.print("Enter the x coordinate of point " + Integer.toString(i+1) + ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          x = Integer.valueOf(line);
        }

        System.out.print("Enter the y coordinate of point " + Integer.toString(i+1) + ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          y = Integer.valueOf(line);
        }

        simplePolygonArgs[i] = new Point(x, y);
      }

      shape2DToReturn = new SimplePolygon(simplePolygonArgs);
      break;

    // triangle
    case 3:
      Point[] triangleArgs = new Point[3];

      for (int i = 0; i < 3; i++) {
        int x, y;
        System.out.print("Enter the x coordinate of point " + Integer.toString(i+1) + ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          x = Integer.valueOf(line);
        }

        System.out.print("Enter the y coordinate of point " + Integer.toString(i+1) + ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          y = Integer.valueOf(line);
        }

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
        int x, y;
        System.out.print("Enter the x coordinate of point " + Integer.toString(i+1) + ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          x = Integer.valueOf(line);
        }

        System.out.print("Enter the y coordinate of point " + Integer.toString(i+1) + ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          y = Integer.valueOf(line);
        }

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
      prompts = Parallelogram.PROMPTS;
      int[] parallelogramArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print("Enter the " + prompts[i]+ ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          parallelogramArgs[i] = Integer.valueOf(line);
        }
      }


      shape2DToReturn = new Parallelogram(
        new Point (parallelogramArgs[0], parallelogramArgs[1]),
        new Point (parallelogramArgs[2], parallelogramArgs[3]),
        parallelogramArgs[4]
      );

      break;

    // trapezoid
    case 6:
      prompts = Trapezoid.PROMPTS;
      int[] trapezoidArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print("Enter the " + prompts[i]+ ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          trapezoidArgs[i] = Integer.valueOf(line);
        }
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
      prompts = Rectangle.PROMPTS;
      int[] rectangleArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print("Enter the " + prompts[i]+ ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          rectangleArgs[i] = Integer.valueOf(line);
        }
      }

      shape2DToReturn = new Rectangle(
        rectangleArgs[0],
        rectangleArgs[1],
        rectangleArgs[2],
        rectangleArgs[3]
      );
      break;

    // rhombus
    case 8:
      prompts = Rhombus.PROMPTS;
      int[] rhombusArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print("Enter the " + prompts[i]+ ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          rhombusArgs[i] = Integer.valueOf(line);
        }
      }

      shape2DToReturn = new Rhombus(
        new Point(rhombusArgs[0], rhombusArgs[1]),
        new Point(rhombusArgs[2], rhombusArgs[3])
      );
      break;

    // square
    case 9:
      prompts = Square.PROMPTS;
      int[] squareArgs = new int[prompts.length];

      for (int i = 0; i < prompts.length; i++) {
        System.out.print("Enter the " + prompts[i]+ ": ");
        line = input.nextLine();
        if (!isInteger(line)) {
          throw new NumberFormatException();
        } else {
          squareArgs[i] = Integer.valueOf(line);
        }
      }

      shape2DToReturn = new Square(
        squareArgs[0],
        squareArgs[1],
        squareArgs[2]
      );
      break;
    }

    if (!Shape2D.isValidShape2D(shape2DToReturn)) {
      throw new InvalidShapeException("InvalidShapeException");
    }
    return shape2DToReturn;
    
  }

  public static int getIntInRangeFromInput(Scanner input, int min, int max)
    throws NumberFormatException,
           IntNotInRangeException {

    String userInput = input.nextLine();
    if (!isInteger(userInput)) {
      throw new NumberFormatException();
    }
    int userInt = Integer.valueOf(userInput);
    if (!isInRange(userInt, min, max)) {
      throw new IntNotInRangeException();
    }
    return userInt;
  }

  public static String toNumberedString(String[] strings) {
    String result = "";
    for (int i = 0; i < strings.length; i++) {
      result += Integer.toString(i+1) + ". " + strings[i] + "\n";
    }
    return result.trim();
  }

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

  public static boolean isInRange(int number, int min, int max) {
    return (number >= min) && (number <= max);
  }


  public static class GeometryScreen {
    GeometryScreen(int width, int height) {
      frame = new JFrame("Geometry Drawing Program 1.0");
      
      //Create a new "custom" panel for graphics based on the inner class below
      JPanel graphicsPanel = new GraphicsPanel();
      
      //Add the panel and the frame to the window
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
        setDoubleBuffered(true);
        g.setColor(Color.BLACK);

        this.drawAxis(g);
        
        if (!shape2Ds.isEmpty()) {
          for(int i = 0; i < shape2Ds.size(); i++) {
            shape2Ds.get(i).draw((Graphics2D)g);
          }
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