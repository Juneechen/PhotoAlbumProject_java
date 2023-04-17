package model;

import java.awt.Color;

import views.IShapeVisitor;

/**
 * an IShape interface for the ShapeAlbum.
 */
public interface IShape {
  /**
   * return the name of the shape.
   *
   * @return name of the shape.
   */
  String getName();

  /**
   * return the x coordinate of the shape.
   *
   * @return x coordinate of the shape.
   */
  int getX();

  /**
   * return the y coordinate of the shape.
   *
   * @return y coordinate of the shape.
   */
  int getY();

  /**
   * return the x dimension of the shape.
   *
   * @return x dimension of the shape; could be width or x radius.
   */
  int getWidth();

  /**
   * return the y dimension of the shape.
   *
   * @return y dimension of the shape; could be height or y radius.
   */
  int getHeight();

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
   * @param w desired x dimension.
   * @param h desired y dimension.
   */
  void resize(int w, int h);

  /**
   * make a copy of the shape.
   * @return a deep copy of the shape.
   */
  IShape copy();

  /**
   * accept a visitor and have the visitor visit self.
   * @param visitor a visitor.
   */
  void accept(IShapeVisitor visitor);
}
