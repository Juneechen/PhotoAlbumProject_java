package album;

import java.util.Objects;

/**
 * an abstract shape class.
 */
public abstract class ShapeImpl implements IShape {
  protected double xDimension, yDimension;
  protected Point position;
  protected Color color;

  /**
   * Construct a Shape object with the given parameters, assume all info are valid.
   * Should only be used through the {@link ShapeFactory} class
   * that ensures all parameter are valid for a shape.
   *
   * @param color fill color.
   * @param xSize x dimension (>0) of the shape; could be width or x radius.
   * @param ySize y dimension (>0) of the shape; could be height or y radius.
   * @param xCord x coordinate of the bottom left corner or center.
   * @param yCord y coordinate of the bottom left corner or center.
   */
  public ShapeImpl (Color color, double xSize, double ySize, double xCord, double yCord) {
    //    if (colorName == null || colorName.isEmpty() || xSize <= 0 || ySize <= 0) {
    //      throw new IllegalArgumentException("invalid shape info.\n");
    //    }
    this.color = color;
    this.xDimension = xSize;
    this.yDimension = ySize;
    this.position = new Point(xCord, yCord);
  }

  @Override
  public Point getPosition() {
    return this.position;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setPosition(double x, double y) {
    this.position = new Point(x, y);
  }

  @Override
  public void setColor(Color c) {
    if (c == null) {
      return;
    }
    this.color = c;
  }

  @Override
  public void resize(double dx, double dy) {
    this.xDimension = dx;
    this.yDimension = dy;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.xDimension, this.yDimension, this.position, this.color);
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
    return Double.compare(this.xDimension, o.xDimension) == 0
            && Double.compare(this.yDimension, o.yDimension) == 0
            && this.position.equals(o.position)
            && this.color.equals(o.color);
  }
}
