package album;

public enum Color {
  RED(java.awt.Color.red), BLUE(java.awt.Color.blue), GREEN(java.awt.Color.green);

  private final java.awt.Color color;

  private Color(java.awt.Color c) {
    this.color = c;
  }

  @Override
  public String toString() {
    float red = color.getRed() / 255.0f;
    float green = color.getGreen() / 255.0f;
    float blue = color.getBlue() / 255.0f;
    return String.format("Color: (%.1f,%.1f,%.1f)", red, green, blue);
  }
}
