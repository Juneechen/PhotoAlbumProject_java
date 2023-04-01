package album;

/**
 * an IShape interface for the ShapeAlbum.
 */
public interface IShape {
  /**
   * return the position of the shape.
   *
   * @return a 2D Point representing a shape's position.
   */
  Point getPosition();

  /**
   * return the Color of the shape.
   *
   * @return color of the shape.
   */
  Color getColor();

  /**
   * move the shape to the target position.
   *
   * @param x x coordinate of the target position.
   * @param y y coordinate of the target position.
   */
  void setPosition(double x, double y);

  /**
   * reset color of the shape.
   * Should only be used through the {@link ShapeFactory} class
   * that ensures all parameter are valid for a shape.
   *
   * @param colorName representing the name of the desired color.
   */
  void setColor(Color colorName);

  /**
   * resize the shape.
   * Should only be used through the {@link ShapeFactory} class
   * that ensures all parameter are valid for a shape.
   *
   * @param dx change in width or x radius, positive: make bigger, negative: make smaller.
   * @param dy change in height or y radius, positive: make bigger, negative: make smaller.
   */
  void resize(double dx, double dy);

  /**
   * make a copy of the shape.
   * @return a deep copy of the shape.
   */
  IShape copy();
}
