import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.IShape;
import model.Oval;
import model.Rectangle;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * unit tests for ShapeImpl. Only basic valid cases will be tested here, 
 * all invalid cases should be checked and tested in ShapeFactory test.
 */
public class ShapeImplTest {
  private IShape oval = new Oval("O", 3, 7, 10, 12, Color.RED);
  private IShape rec = new Rectangle("R", 0, 0, 22, 33, Color.BLUE);

  /**
   * test constructor and getters; no criteria applied here, 
   * validity is checked by ShapeFactory before this method is being called.
   */
  @Test
  public void testConstructorAndGetters() {
    Assert.assertEquals(3, oval.getX());
    Assert.assertEquals(7, oval.getY());
    assertEquals(Color.RED, oval.getColor());
    assertEquals(10, oval.getWidth());
    assertEquals(12, oval.getHeight());
  }

  /**
   * test setPosition(), no restriction for now.
   */
  @Test
  public void testSetPosition() {
    oval.setPosition(0, 0);
    Assert.assertEquals(0, oval.getX());
    Assert.assertEquals(0, oval.getY());
  }

  /**
   * test setColor(): no criteria applied here, 
   * validity is checked by ShapeFactory before this method is being called.
   */
  @Test
  public void testSetColor() {
    assertEquals(Color.RED, oval.getColor()); // current
    oval.setColor(Color.BLUE); // set to blue
    assertEquals(Color.BLUE, oval.getColor());
  }

  /**
   * test resize(): no criteria applied here, 
   * validity is checked by ShapeFactory before this method is being called.
   */
  @Test
  public void testResize() {
    oval.resize(10, 15);
    assertEquals(10, oval.getWidth());
    assertEquals(15, oval.getHeight());
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
    assertEquals(new Oval("O", 3, 7, 10, 12, Color.RED), oval);
    // different in one of the properties:
    assertNotEquals(new Rectangle("R", 10, 12, 3, 7, Color.RED), oval);
    assertNotEquals(new Oval("O", 3, 7, 10, 12, Color.BLUE), oval);
    assertNotEquals(new Oval("O", 4, 7, 10, 12, Color.RED), oval);
    assertNotEquals(new Oval("O", 3, 8, 10, 12, Color.RED), oval);
    assertNotEquals(new Oval("O", 3, 7, 11, 12, Color.RED), oval);
    assertNotEquals(new Oval("O", 3, 6, 10, 11, Color.RED), oval);
  }

  /**
   * test toString().
   */
  @Test
  public void testToString() {
    assertEquals("Name: R\nType: rectangle\n" +
            "Min Corner: (0, 0), Width: 22, Height: 33, Color: (0, 0, 255)", rec.toString());
    assertEquals("Name: O\nType: oval\n" +
            "Center: (3, 7), X radius: 10, Y radius: 12, Color: (255, 0, 0)", oval.toString());
  }
}