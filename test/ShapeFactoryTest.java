import static org.junit.Assert.assertEquals;

import model.IShape;
import model.ShapeFactory;
import org.junit.Test;

import java.awt.*;

/**
 * ShapeFactory unit tests.
 */
public class ShapeFactoryTest {
  /**
   * test create(): valid cases.
   */
  @Test
  public void testCreate() {
    IShape oval = ShapeFactory.create("O", "oval", 10, 12, 3, 7, Color.RED);
    assertEquals("Name: O\nType: oval\n"
            + "Center: (10, 12), X radius: 3, Y radius: 7, Color: (255, 0, 0)", oval.toString());

    // edge case for dimension and all cap for kind and color:
    IShape rec = ShapeFactory.create("R", "RECTANGLE", 0, 0, 1, 33, Color.BLUE);
    assertEquals("Name: R\nType: rectangle\nMin Corner: (0, 0), "
            + "Width: 1, Height: 33, Color: (0, 0, 255)", rec.toString());
  }

  /**
   * test create(): invalid x dimension
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateInvalidSizeX() {
    IShape oval = ShapeFactory.create("O", "oval", 3, 7, 0, 12, Color.RED);
  }

  /**
   * test create(): invalid y dimension
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateInvalidSizeY() {
    IShape oval = ShapeFactory.create("O", "oval", 3, 7, 10, -1, Color.RED);
  }

  /**
   * test create(): unknown kind
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateUnknownKind() {
    IShape oval = ShapeFactory.create("T", "Triangle", 10, 10, 3, 7, Color.RED);
  }

  /**
   * test create(): Empty kind
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateEmptyKind() {
    IShape oval = ShapeFactory.create("O", "", 10, 10, 3, 7, Color.RED);
  }

  /**
   * test create(): null kind
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateNullKind() {
    IShape oval = ShapeFactory.create("O", null, 10, 10, 3, 7, Color.RED);
  }

  /**
   * test create(): null color
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateNullColor() {
    IShape oval = ShapeFactory.create("O", "Oval", 10, 10, 3, 7, null);
  }

  /**
   * test changeSize(): valid cases
   */
  @Test
  public void testChangeSize() {
    IShape oval = ShapeFactory.create("O", "oval", 3, 7, 10, 12, Color.RED);
    ShapeFactory.changeSize(oval, 2, 1);
    assertEquals("Name: O\nType: oval\nCenter: (3, 7), "
            + "X radius: 2, Y radius: 1, Color: (255, 0, 0)", oval.toString());
  }

  /**
   * test changeSize(): invalid dimension
   */
  @Test
  public void testChangeSizeInvalid() {
    IShape oval = ShapeFactory.create("O", "oval", 3, 7, 10, 12, Color.RED);
    ShapeFactory.changeSize(oval, 10, 0);
    // should remain unchanged:
    assertEquals("Name: O\nType: oval\nCenter: (3, 7), "
            + "X radius: 10, Y radius: 12, Color: (255, 0, 0)", oval.toString());
  }

  /**
   * test changeColor(): valid case
   */
  @Test
  public void testChangeColor() {
    IShape oval = ShapeFactory.create("O", "oval", 3, 7,10, 12,  Color.RED);
    ShapeFactory.changeColor(oval, Color.GREEN);

    assertEquals("Name: O\nType: oval\nCenter: (3, 7), "
            + "X radius: 10, Y radius: 12, Color: (0, 255, 0)", oval.toString());
  }
}