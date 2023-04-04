package album;

/**
 * enum Color representing the following supported colors for ShapesAlbum:
 * RED, BLUE, GREEN.
 */
public enum Color {
  RED(java.awt.Color.red), BLUE(java.awt.Color.blue), GREEN(java.awt.Color.green);

  private final java.awt.Color color;

  /**
   * construct the Color with a corresponding representation from the java.awt.Color class.
   * @param c the corresponding color from the java.awt.Color class.
   */
  private Color(java.awt.Color c) {
    this.color = c;
  }

  /**
   * the CSS representation of a color.
   * @return the CSS representation of a color.
   */
  @Override
  public String toString() {
    float red = color.getRed() / 255.0f;
    float green = color.getGreen() / 255.0f;
    float blue = color.getBlue() / 255.0f;
    return String.format("Color: (%.1f,%.1f,%.1f)", red, green, blue);
  }
}
