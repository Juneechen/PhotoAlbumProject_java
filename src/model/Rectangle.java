package model;

import java.awt.Color;

public class Rectangle extends ShapeImpl {
  /**
   * Construct a Rectangle object with the given parameters, assume all info are valid.
   * Should only be used through the {@link ShapeFactory} class
   * that ensures all parameter are valid for a shape.
   *
   * @param xCord x coordinate of the bottom left corner or center.
   * @param yCord y coordinate of the bottom left corner or center.
   * @param width x dimension (>0) of the shape; could be width or x radius.
   * @param height y dimension (>0) of the shape; could be height or y radius.
   * @param color name of the fill color.
   */
  public Rectangle(String name, int xCord, int yCord,
                   int width, int height, Color color) {
    super(name, xCord, yCord, width, height, color);
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.name, this.position.getX(), this.position.getY(),
            this.xDimension, this.yDimension, this.color);
  }

  @Override
  public String toString() {
    return String.format("Name: %s\nType: rectangle\n"
            + "Min corner: %s, Width: %d, Height: %d, %s", this.name,
            this.position.toString(), this.xDimension, this.yDimension, this.color.toString());
  }
}