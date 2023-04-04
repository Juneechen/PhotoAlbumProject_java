import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import album.Color;
import album.IShape;
import album.Oval;
import album.Rectangle;
import album.Snapshot;
import org.junit.Before;
import org.junit.Test;

/**
 * unit tests for Snapshot class.
 */
public class SnapshotTest {
  private Snapshot snapshotEmpty = new Snapshot("empty", new ArrayList<>());
  private Snapshot snapshot;
  private IShape rec = new Rectangle("R", Color.RED, 50, 100, 200, 200);
  private IShape oval = new Oval("O", Color.BLUE, 60, 30, 500, 100);

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
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)",
            snapshot.getShapes().get(0).toString());
    assertEquals("Name: O\nType: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)",
            snapshot.getShapes().get(1).toString());
  }

  /**
   * test getShapes(),
   * make sure the returned list is unmodifiable.
   */
  @Test (expected = UnsupportedOperationException.class)
  public void getShapesUnmodifiable() {
    snapshot.getShapes().add(new Oval("O", Color.GREEN, 60, 30, 500, 100));
  }
}