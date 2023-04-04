import static org.junit.Assert.assertEquals;

import album.IShape;
import album.ShapeFactory;
import org.junit.Test;

public class ShapeFactoryTest {
  /**
   * test create(): valid cases
   */
  @Test
  public void testCreate() {
    IShape oval = ShapeFactory.create("O", "oval", "red", 10, 12.56, 3.66, 7);
    assertEquals("Name: O\nType: oval\nCenter: (3.7,7.0), "
            + "X radius: 10.0, Y radius: 12.6, Color: (1.0,0.0,0.0)", oval.toString());

    // edge case for dimension and all cap for kind and color:
    IShape rec = ShapeFactory.create("R", "RECTANGLE", "BLUE", 0.1, 33, 0, 0);
    assertEquals("Name: R\nType: rectangle\nMin corner: (0.0,0.0), "
            + "Width: 0.1, Height: 33.0, Color: (0.0,0.0,1.0)", rec.toString());
  }

  /**
   * test create(): invalid x dimension
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateInvalidSizeX() {
    IShape oval = ShapeFactory.create("O", "oval", "red", 0, 12.56, 3.66, 7);
  }

  /**
   * test create(): invalid y dimension
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateInvalidSizeY() {
    IShape oval = ShapeFactory.create("O", "oval", "red", 10, -0.1, 3.66, 7);
  }

  /**
   * test create(): unknown kind
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateUnknownKind() {
    IShape oval = ShapeFactory.create("T", "Triangle", "red", 10, 10, 3.66, 7);
  }

  /**
   * test create(): Empty kind
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateEmptyKind() {
    IShape oval = ShapeFactory.create("O", "", "red", 10, 10, 3.66, 7);
  }

  /**
   * test create(): null kind
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateNullKind() {
    IShape oval = ShapeFactory.create("O", null, "red", 10, 10, 3.66, 7);
  }

  /**
   * test create(): unknown color
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateUnknownColor() {
    IShape oval = ShapeFactory.create("O", "Oval", "yellow", 10, 10, 3.66, 7);
  }

  /**
   * test create(): null color
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateNullColor() {
    IShape oval = ShapeFactory.create("O", "Oval", null, 10, 10, 3.66, 7);
  }

  /**
   * test changeSize(): valid cases
   */
  @Test
  public void testChangeSize() {
    IShape oval = ShapeFactory.create("O", "oval", "red", 10, 12.56, 3.66, 7);
    ShapeFactory.changeSize(oval, 2, 0.1);
    assertEquals("Name: O\nType: oval\nCenter: (3.7,7.0), "
            + "X radius: 2.0, Y radius: 0.1, Color: (1.0,0.0,0.0)", oval.toString());
  }

  /**
   * test changeSize(): invalid dimension
   */
  @Test
  public void testChangeSizeInvalid() {
    IShape oval = ShapeFactory.create("O", "oval", "red", 10, 12.56, 3.66, 7);
    ShapeFactory.changeSize(oval, 10, 0);
    // should remain unchanged:
    assertEquals("Name: O\nType: oval\nCenter: (3.7,7.0), "
            + "X radius: 10.0, Y radius: 12.6, Color: (1.0,0.0,0.0)", oval.toString());
  }

  /**
   * test changeColor(): valid case
   */
  @Test
  public void testChangeColor() {
    IShape oval = ShapeFactory.create("O", "oval", "red", 10, 12.56, 3.66, 7);
    ShapeFactory.changeColor(oval, "Green");

    assertEquals("Name: O\nType: oval\nCenter: (3.7,7.0), "
            + "X radius: 10.0, Y radius: 12.6, Color: (0.0,1.0,0.0)", oval.toString());
  }

  /**
   * test changeColor(): unknown Color
   */
  @Test
  public void testChangeColorUnknown() {
    IShape oval = ShapeFactory.create("O", "oval", "red", 10, 12.56, 3.66, 7);
    ShapeFactory.changeColor(oval, "Pink");
    // should remain unchanged:
    assertEquals("Name: O\nType: oval\nCenter: (3.7,7.0), "
            + "X radius: 10.0, Y radius: 12.6, Color: (1.0,0.0,0.0)", oval.toString());
  }
}