package model;

import java.util.Objects;

import java.awt.Color;

/**
 * an abstract shape class.
 */
public abstract class ShapeImpl implements IShape {
  protected String name;
  protected int width, height;
  protected Point position;
  protected Color color;

  /**
   * Construct a Shape object with the given parameters, assume all info are valid.
   * Should only be used through the {@link ShapeFactory} class
   * that ensures all parameter are valid for a shape.
   *
   * @param name identifier of the shape.
   * @param color fill color.
   * @param width x dimension (>0) of the shape; could be width or x radius.
   * @param height y dimension (>0) of the shape; could be height or y radius.
   * @param xCord x coordinate of the bottom left corner or center.
   * @param yCord y coordinate of the bottom left corner or center.
   */
  public ShapeImpl (String name, int xCord, int yCord,
                    int width, int height, Color color) {
    //    if (colorName == null || colorName.isEmpty() || xSize <= 0 || ySize <= 0) {
    //      throw new IllegalArgumentException("invalid shape info.\n");
    //    }
    this.name = name;
    this.color = color;
    this.width = width;
    this.height = height;
    this.position = new Point(xCord, yCord);
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public Point getPosition() {
    return this.position;
  }

  @Override
  public int getX() {
    return this.getPosition().getX();
  }

  @Override
  public int getY() {
    return this.getPosition().getY();
  }

  @Override
  public void setPosition(int x, int y) {
    this.position = new Point(x, y);
  }

  @Override
  public void setColor(Color c) {
    this.color = c;
  }

  @Override
  public void resize(int x, int y) {
    this.width = x;
    this.height = y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.width, this.height, this.position, this.color);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    ShapeImpl o = (ShapeImpl) obj;
    return this.name.equals(o.name) && Double.compare(this.width, o.width) == 0
            && Double.compare(this.height, o.height) == 0
            && this.position.equals(o.position)
            && this.color.equals(o.color);
  }
}
