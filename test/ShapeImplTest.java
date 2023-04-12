import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.Color;
import model.IShape;
import model.Oval;
import model.Point;
import model.Rectangle;
import org.junit.Assert;
import org.junit.Test;

/**
 * unit tests for ShapeImpl. Only basic valid cases will be tested here,
 * all invalid cases should be checked and tested in ShapeFactory test.
 */
public class ShapeImplTest {
  private IShape oval = new Oval("O", Color.RED, 10, 12.56, 3.66, 7);
  private IShape rec = new Rectangle("R", Color.BLUE, 22, 33, 0, 0);

  /**
   * test constructor and getters; no criteria applied here,
   * validity is checked by ShapeFactory before this method is being called.
   */
  @Test
  public void testConstructorAndGetters() {
    Assert.assertEquals(new Point(3.66, 7), oval.getPosition());
    assertEquals("RED", oval.getColor().name());
    assertEquals(10.0, oval.getXDimension(), 10);
    assertEquals(15.5, oval.getYDimension(), 12.56);
  }

  /**
   * test setPosition(), no restriction for now.
   */
  @Test
  public void testSetPosition() {
    oval.setPosition(0, 0);
    assertEquals(new Point(0, 0), oval.getPosition());
  }

  /**
   * test setColor(): no criteria applied here,
   * validity is checked by ShapeFactory before this method is being called.
   */
  @Test
  public void testSetColor() {
    assertEquals("RED", oval.getColor().name()); // current
    oval.setColor(Color.BLUE); // set to blue
    assertEquals("BLUE", oval.getColor().name());
  }

  /**
   * test resize(): no criteria applied here,
   * validity is checked by ShapeFactory before this method is being called.
   */
  @Test
  public void testResize() {
    oval.resize(10.0, 15.5);
    assertEquals(10.0, oval.getXDimension(), 0.01);
    assertEquals(15.5, oval.getYDimension(), 0.01);
  }

  /**
   * test copy(), ensure that it returns a deep copy.
   */
  @Test
  public void testCopy() {
    IShape ovalCopy = oval.copy();
    assertEquals(ovalCopy, oval);

    ovalCopy.resize(16, 19);
    assertNotEquals(ovalCopy, oval);
  }

  /**
   * test equals(), ensure that it checks for type, Color, dimensions, and coordinates.
   */
  @Test
  public void testEquals() {
    IShape other = oval;
    assertEquals(other, oval); // same object
    // same properties:
    assertEquals(new Oval("O", Color.RED, 10, 12.56, 3.66, 7), oval);
    // different in one of the properties:
    assertNotEquals(new Rectangle("R", Color.RED, 10, 12.56, 3.66, 7), oval);
    assertNotEquals(new Oval("O", Color.BLUE, 10, 12.56, 3.66, 7), oval);
    assertNotEquals(new Oval("O", Color.RED, 10.1, 12.56, 3.66, 7), oval);
    assertNotEquals(new Oval("O", Color.RED, 10, 12.6, 3.66, 7), oval);
    assertNotEquals(new Oval("O", Color.RED, 10, 12.56, 3, 7), oval);
    assertNotEquals(new Oval("O", Color.RED, 10, 12.56, 3.66, 6), oval);
  }

  /**
   * test toString().
   */
  @Test
  public void testToString() {
    assertEquals("Name: R\nType: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 22.0, Height: 33.0, Color: (0.0,0.0,1.0)", rec.toString());
    assertEquals("Name: O\nType: oval\n" +
            "Center: (3.7,7.0), X radius: 10.0, Y radius: 12.6, Color: (1.0,0.0,0.0)", oval.toString());
  }
}