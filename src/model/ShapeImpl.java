package model;

import java.util.Objects;

import java.awt.Color;

/**
 * an abstract shape class.
 */
public abstract class ShapeImpl implements IShape {
  protected String name;
  protected int x, y;
  protected int width, height;
  protected Color color;

  @Override
  public String getName() {
    return this.name;
  }

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
    this.x = xCord;
    this.y = yCord;
    this.width = width;
    this.height = height;
    this.color = color;
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
  public Color getColor() {
    return this.color;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void setColor(Color c) {
    this.color = c;
  }

  @Override
  public void resize(int w, int h) {
    this.width = w;
    this.height = h;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.width, this.height, this.x, this.y, this.color);
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
    return this.name.equals(o.name)
            && Double.compare(this.width, o.width) == 0
            && Double.compare(this.height, o.height) == 0
            && this.x == o.getX() && this.y == o.getY()
            && this.color.equals(o.color);
  }

  @Override
  public String toString() {
    return String.format("Center: (%d, %d), X radius: %d, Y radius: %d, Color: (%d, %d, %d)",
            this.x, this.y, this.width, this.height,
            this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }
}
