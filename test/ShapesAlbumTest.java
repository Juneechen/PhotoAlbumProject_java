import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import model.IAlbum;
import model.IShape;
import model.ShapesAlbum;
import org.junit.Before;
import org.junit.Test;
/**
 * unit tests for ShapesAlbum.
 */
public class ShapesAlbumTest {
  private IAlbum album = new ShapesAlbum();

  /**
   * add two valid shapes into the album.
   *
   * @throws Exception for any error.
   */
  @Before
  public void setUp() throws Exception {
    album.add("R", "rectangle", "red", 50, 100, 200, 200);
    album.add("O", "oval", "blue", 60, 30, 500, 100);
  }

  /**
   * test the result of add() in setUp.
   */
  @Test
  public void testAdd() {
    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
            + "Height: 100.0, Color: (1.0,0.0,0.0)\n\n" +
            "Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,0.0,1.0)", album.toString());
  }


  /**
   * test add() with duplicate name/identifier.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddDuplicate() {
    album.add("R", "rectangle", "green", 100, 80, 0, 0);
  }

  /**
   * test add() with empty name/identifier.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddEmptyName() {
    album.add("", "rectangle", "green", 100, 80, 0, 0);
  }

  /**
   * test add() with null name/identifier.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddNullName() {
    album.add(null, "rectangle", "green", 100, 80, 0, 0);
  }

  /**
   * test add() with invalid parameters other than name;
   * those are checked by ShapeFactory and tested in tests for ShapeFactory,
   * if invalid, ignore add() without throwing exception.
   */
  @Test
  public void testAddInvalid() {
    // unknown type
    album.add("T", "triangle", "green", 100, 80, 0, 0);
    // unknown color
    album.add("R1", "rectangle", "yellow", 100, 80, 0, 0);
    // invalid x dimension
    album.add("R2", "rectangle", "green", 0, 80, 0, 0);
    // unknown y dimension
    album.add("R3", "rectangle", "green", 100, -0.1, 0, 0);

    // check album, none of the above should be added:
    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
            + "Height: 100.0, Color: (1.0,0.0,0.0)\n\n" +
            "Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,0.0,1.0)", album.toString());
  }

  /**
   * test remove(): valid element.
   */
  @Test
  public void testRemove() {
    IShape target = album.remove("R");

    // check album, R should be gone
    assertEquals("Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,0.0,1.0)", album.toString());
  }

  /**
   * test remove(): null, empty, and non-existing identifier.
   */
  @Test
  public void testRemoveInvalid() {
    IShape target = album.remove(null);
    assertNull(target);

    target = album.remove("");
    assertNull(target);

    target = album.remove("K");
    assertNull(target);

    // check album, should remain unchanged:
    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
            + "Height: 100.0, Color: (1.0,0.0,0.0)\n\n" +
            "Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,0.0,1.0)", album.toString());
  }

  /**
   * test move(): valid case.
   */
  @Test
  public void testMove() {
    album.move("R", 33, 33);

    // check album, R's position should be changed:
    assertEquals("Name: R\nType: rectangle\nMin corner: (33.0,33.0), Width: 50.0, "
            + "Height: 100.0, Color: (1.0,0.0,0.0)\n\n" +
            "Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,0.0,1.0)", album.toString());
  }

  /**
   * test move(): null, empty, and non-existing identifier.
   */
  @Test
  public void testMoveInvalid() {
    album.move("K", 33, 33);
    album.move("", 33, 33);
    album.move(null, 33, 33);

    // check album, should remain unchanged:
    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
            + "Height: 100.0, Color: (1.0,0.0,0.0)\n\n" +
            "Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,0.0,1.0)", album.toString());
  }

  /**
   * test resize(): valid case.
   */
  @Test
  public void testResize() {
    album.resize("O", 66, 66);

    // check album, O's dimensions should be changed:
    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
            + "Height: 100.0, Color: (1.0,0.0,0.0)\n\n" +
            "Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 66.0, "
            + "Y radius: 66.0, Color: (0.0,0.0,1.0)", album.toString());
  }

  /**
   * test resize(): valid identifier or dimension.
   */
  @Test
  public void testResizeInvalid() {
    album.resize("K", 66, 66);
    album.resize("R", 0, 66);
    album.resize("R", 66, 0);

    // check album
    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
            + "Height: 100.0, Color: (1.0,0.0,0.0)\n\n" +
            "Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,0.0,1.0)", album.toString());
  }

  /**
   * test changeColor(): valid case.
   */
  @Test
  public void changeColor() {
    album.changeColor("O", "green");

    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
            + "Height: 100.0, Color: (1.0,0.0,0.0)\n\n" +
            "Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,1.0,0.0)", album.toString());
  }

  /**
   * test changeColor(): unknown color or identifier.
   */
  @Test
  public void changeColorInvalid() {
    album.changeColor("O1", "green");
    album.changeColor("O", "pink");

    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
            + "Height: 100.0, Color: (1.0,0.0,0.0)\n\n" +
            "Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,0.0,1.0)", album.toString());
  }

  /**
   * test takeSnapshot().
   */
  @Test
  public void testTakeSnapshot() {
    album.takeSnapshot("test");
    assertEquals(1, album.getSnapshots().size());
  }

  /**
   * test resetSnapshots().
   */
  @Test
  public void testResetSnapshots() {
    album.takeSnapshot("test");
    assertEquals(1, album.getSnapshots().size());
    album.resetSnapshots();
    assertEquals(0, album.getSnapshots().size());
  }

  /**
   * test makeEmpty(), make sure it clear the album without clearing snapshots.
   */
  @Test
  public void testMakeEmpty() {
    album.takeSnapshot("test");

    album.makeEmpty();
    assertEquals(1, album.getSnapshots().size());
    assertEquals("", album.toString());
  }
}