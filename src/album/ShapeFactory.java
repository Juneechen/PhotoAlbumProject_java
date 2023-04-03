package album;

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
   * @param kind type of the shape; current valid types: oval, rectangle.
   * @param color desired fill color.
   * @param xSize x dimension (>0) of the shape; could be width or x radius.
   * @param ySize y dimension (>0) of the shape; could be height or y radius.
   * @param xCord x coordinate of the bottom left corner or center.
   * @param yCord y coordinate of the bottom left corner or center.
   * @return the desired shape.
   * @throws IllegalArgumentException if any info is invalid.
   */
  public static IShape create(String kind, String color,
                              double xSize, double ySize, double xCord, double yCord)
                              throws IllegalArgumentException {
    // check for validity:
    if (kind == null || kind.isEmpty()) {
      throw new IllegalArgumentException("unknown shape type.\n");
    }
    Color c = strToColor(color);
    if (strToColor(color) == null) {
      throw new IllegalArgumentException("invalid color.\n");
    }
    if (!isValidSize(xSize, ySize)) {
      throw new IllegalArgumentException("invalid shape size.\n");
    }

    // identify the type of shape to create:
    if (kind.equalsIgnoreCase("Oval")) {
          return new Oval(c, xSize, ySize, xCord, yCord);
    }
    if (kind.equalsIgnoreCase("Rectangle")) {
      return new Rectangle(c, xSize, ySize, xCord, yCord);
    }
    // else, unknown type
    return null;
  }

  /**
   * change the size of a shape if the given dimensions are valid, do nothing otherwise.
   * @param shape the shape object to be changed.
   * @param newX desired x dimension.
   * @param newY desired y dimension.
   */
  public static void changeSize(IShape shape, double newX, double newY) {
    if (!isValidSize(newX, newY)) {
      return;
    }
    shape.resize(newX, newY);
  }

  /**
   * change the color of a shape if the given color is valid, do nothing otherwise.
   * @param shape the shape object to be changed.
   * @param newColor name of the desired color.
   */
  public static void changeColor(IShape shape, String newColor) {
    Color newC = strToColor(newColor);
    if (newC == null) {
      return;
    }
    shape.setColor(newC);
  }

  /**
   * answer whether a given name is a valid color name.
   * 
   * @param name name of the color.
   * @return the corresponding Color if the name represents a known color, null otherwise.
   */
  public static Color strToColor(String name) {
    if (name == null) {
      return null;
    }
    try {
      return Color.valueOf(name.toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  /**
   * answer whether a given set of dimensions is valid.
   *
   * @param x x dimension (>0) of the shape; could be width or x radius.
   * @param y y dimension (>0) of the shape; could be height or y radius.
   * @return true if both are greater than 0, false otherwise.
   */
  private static boolean isValidSize(double x, double y) {
    return (x > 0 && y > 0);
  }
}