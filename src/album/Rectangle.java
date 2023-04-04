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
  public Rectangle(String name, Color colorName, double xSize, double ySize, double xCord, double yCord) {
    super(name, colorName, xSize, ySize, xCord, yCord);
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.name, this.color, this.xDimension, this.yDimension,
            this.position.getX(), this.position.getY());
  }

  @Override
  public String toString() {
    return String.format("Name: %s\nType: rectangle\n"
            + "Min corner: %s, Width: %.1f, Height: %.1f, %s", this.name,
            this.position.toString(), this.xDimension, this.yDimension, this.color.toString());
  }
}
