package model;

import java.awt.Color;

/**
 * A ShapeFactory that knows the supported types, colors and properties of shapes.
 * It generates and modifies shapes if all information are valid.
 * Current known types of shape are: oval, rectangle.
 */
public class ShapeFactory {
  /**
   * given a set of shape properties, checks for validity,
   * and generate the desired shape if all info are valid.
   *
   * @param name identifier of the shape.
   * @param kind type of the shape; current valid types: oval, rectangle.
   * @param color desired fill color.
   * @param width x dimension (>0) of the shape; could be width or x radius.
   * @param height y dimension (>0) of the shape; could be height or y radius.
   * @param xCord x coordinate of the bottom left corner or center.
   * @param yCord y coordinate of the bottom left corner or center.
   * @return the desired shape.
   * @throws IllegalArgumentException if any info is invalid.
   */
  public static IShape create(String name, String kind, int xCord, int yCord,
                              int width, int height, Color color)
                              throws IllegalArgumentException {
    // check for validity:
    if (kind == null || kind.isEmpty()) {
      throw new IllegalArgumentException("unknown shape type.\n");
    }
//    Color c = strToColor(color);
//    if (strToColor(color) == null) {
//      throw new IllegalArgumentException("invalid color.\n");
//    }
    if (invalidSize(width, height)) {
      throw new IllegalArgumentException("invalid shape size.\n");
    }

    // identify the type of shape to create:
    if (kind.equalsIgnoreCase("Oval")) {
          return new Oval(name, xCord, yCord, width, height, color);
    }
    if (kind.equalsIgnoreCase("Rectangle")) {
      return new Rectangle(name, xCord, yCord, width, height, color);
    }
    // else, unknown type
    throw new IllegalArgumentException("unknown type");
  }

  /**
   * change the size of a shape if the given dimensions are valid, do nothing otherwise.
   * @param shape the shape object to be changed.
   * @param newX desired x dimension.
   * @param newY desired y dimension.
   */
  public static void changeSize(IShape shape, int newX, int newY) {
    if (invalidSize(newX, newY)) {
      return;
    }
    shape.resize(newX, newY);
  }

  /**
   * change the color of a shape if the given color is valid, do nothing otherwise.
   * @param shape the shape object to be changed.
   * @param newColor name of the desired color.
   */
  public static void changeColor(IShape shape, Color newColor) {
    shape.setColor(newColor);
  }

  /**
   * answer whether a given name is a valid color name.
   * 
   * @param name name of the color.
   * @return the corresponding Color if the name represents a known color, null otherwise.
   */
//  private static Color strToColor(String name) {
//    if (name == null) {
//      return null;
//    }
//    try {
//      return Color.valueOf(name.toUpperCase());
//    } catch (IllegalArgumentException e) {
//      return null;
//    }
//  }

  /**
   * answer whether a given set of dimensions is valid.
   *
   * @param x x dimension (>0) of the shape; could be width or x radius.
   * @param y y dimension (>0) of the shape; could be height or y radius.
   * @return true if both are greater than 0, false otherwise.
   */
  private static boolean invalidSize(int x, int y) {
    return (x <= 0 || y <= 0);
  }
}
