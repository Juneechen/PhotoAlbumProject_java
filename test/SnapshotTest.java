import static org.junit.Assert.assertEquals;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.IShape;
import model.Oval;
import model.Rectangle;
import model.Snapshot;
import org.junit.Before;
import org.junit.Test;

/**
 * unit tests for Snapshot class.
 */
public class SnapshotTest {
  private Snapshot snapshotEmpty = new Snapshot("empty", new ArrayList<>());
  private Snapshot snapshot;
  private IShape rec = new Rectangle("R", 200, 200, 50, 100, Color.RED);
  private IShape oval = new Oval("O", 500, 100, 60, 30, Color.BLUE);

  @Before
  public void setUp() throws Exception {
    List<IShape> shapes = new ArrayList<>();
    shapes.add(rec);
    shapes.add(oval);
    snapshot = new Snapshot("sn1", shapes);
  }

  /**
   * test getShapes(), 
   * make sure it returns the list containing the shapes in insertion order, 
   * and that the list is unmodifiable.
   */
  @Test
  public void getShapes() {
    assertEquals(2, snapshot.getShapes().size());
    assertEquals("Name: R\nType: rectangle\n"
            + "Min Corner: (200, 200), Width: 50, Height: 100, Color: (255, 0, 0)",
            snapshot.getShapes().get(0).toString());
    assertEquals("Name: O\nType: oval\n"
            + "Center: (500, 100), X radius: 60, Y radius: 30, Color: (0, 0, 255)",
            snapshot.getShapes().get(1).toString());
  }

  /**
   * test getShapes(), 
   * make sure the returned list is unmodifiable.
   */
  @Test (expected = UnsupportedOperationException.class)
  public void getShapesUnmodifiable() {
    snapshot.getShapes().add(new Oval("O", 60, 30, 500, 100, Color.GREEN));
  }
}