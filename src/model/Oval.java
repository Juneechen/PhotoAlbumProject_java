package model;

import java.awt.Color;

import views.IShapeVisitor;

public class Oval extends ShapeImpl {
  /**
   * Construct an Oval object with the given parameters, assume all info are valid.
   * Should only be used through the {@link ShapeFactory} class
   * that ensures all parameter are valid for a shape.
   *
   * @param xCord x coordinate of the bottom left corner or center.
   * @param yCord y coordinate of the bottom left corner or center.
   * @param width x dimension (>0) of the shape; could be width or x radius.
   * @param height y dimension (>0) of the shape; could be height or y radius.
   * @param color name of the fill color.
   */
  public Oval(String name, int xCord, int yCord, int width, int height, Color color) {
    super(name, xCord, yCord, width, height, color);
  }

  @Override
  public IShape copy() {
    return new Oval(this.name, this.x, this.y, this.width, this.height, this.color);
  }

  @Override
  public void accept(IShapeVisitor visitor) {
    visitor.visit((Oval) this.copy());
  }

  @Override
  public String toString() {
    return String.format("Name: %s\nType: oval\n", this.name) + super.toString();
  }
}
