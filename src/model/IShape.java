package model;

import java.awt.Color;

/**
 * an IShape interface for the ShapeAlbum.
 */
public interface IShape {
  /**
   * return the Color of the shape.
   *
   * @return color of the shape.
   */
  Color getColor();

  /**
   * return the x dimension of the shape.
   *
   * @return x dimension of the shape; could be width or x radius.
   */
  int getXDimension();

  /**
   * return the y dimension of the shape.
   *
   * @return y dimension of the shape; could be height or y radius.
   */
  int getYDimension();

  /**
   * return the position of the shape.
   *
   * @return a 2D Point representing a shape's position.
   */
  Point getPosition();

  /**
   * move the shape to the target position.
   *
   * @param x x coordinate of the target position.
   * @param y y coordinate of the target position.
   */
  void setPosition(int x, int y);

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
   * @param x desired x dimension.
   * @param y desired y dimension.
   */
  void resize(int x, int y);

  /**
   * make a copy of the shape.
   * @return a deep copy of the shape.
   */
  IShape copy();

  void accept(IShapeVisitor visitor);
}
