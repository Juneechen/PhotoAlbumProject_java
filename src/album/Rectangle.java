package album;

public class Rectangle extends ShapeImpl {
  /**
   * Construct a Rectangle object with the given parameters, assume all info are valid.
   * Should only be used through the {@link ShapeFactory} class
   * that ensures all parameter are valid for a shape.
   *
   * @param colorName name of the fill color.
   * @param xSize x dimension (>0) of the shape; could be width or x radius.
   * @param ySize y dimension (>0) of the shape; could be height or y radius.
   * @param xCord x coordinate of the bottom left corner or center.
   * @param yCord y coordinate of the bottom left corner or center.
   */
  public Rectangle(Color colorName, double xSize, double ySize, double xCord, double yCord) {
    super(colorName, xSize, ySize, xCord, yCord);
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.color, this.xDimension, this.yDimension,
            this.position.getX(), this.position.getY());
  }

  @Override
  public String toString() {
    return String.format("Type: rectangle\nMin corner: %s, Width: %.1f,Height: %.1f, %s",
            this.position.toString(), this.xDimension, this.yDimension, this.color.toString());
  }
}
