package album;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ShapeImplTest {
  private IShape oval = new Oval(Color.RED, 10, 12.56, 3.66, 7);
  private IShape rec = new Rectangle(Color.BLUE, 22, 33, 0, 0);
  @Before
  public void setUp() throws Exception {
  }

  /**
   * test getPosition() and getColor().
   */
  @Test
  public void testGetter() {
    assertEquals(new Point(3.66, 7), oval.getPosition());
    assertEquals("RED", oval.getColor().name());
  }


  @Test
  public void setPosition() {
    oval.setPosition(0, 0);
    assertEquals(new Point(0, 0), oval.getPosition());
  }

  @Test
  public void setColor() {
    assertEquals("RED", oval.getColor().name()); // current
    oval.setColor(Color.BLUE); // set to blue
    assertEquals("BLUE", oval.getColor().name());
  }


  @Test
  public void resize() {

  }

  @Test
  public void testHashCode() {
  }

  @Test
  public void testEquals() {
  }
}