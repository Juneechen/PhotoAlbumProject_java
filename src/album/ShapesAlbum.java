package album;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A Shapes Album class representing a simple "photo album" from shapes.
 */
public class ShapesAlbum {
  private Map<String, IShape> allShapes = new HashMap<>();
  private List<Snapshot> snapshots = new ArrayList<>();
  private List<String> eventLog = new ArrayList<>();

  /**
   * add a shape into the album given a unique identifier and a set of shape properties.
   *
   * @param identifier a unique name for the shape.
   * @param kind type of the shape.
   * @param color desired fill color.
   * @param xSize x dimension of the shape; could be width or x radius.
   * @param ySize y dimension of the shape; could be height or y radius.
   * @param xCord x coordinate to place the shape.
   * @param yCord y coordinate to place the shape.
   */
  public void placeShape(String identifier, String kind, String color, double xSize, double ySize, double xCord, double yCord) {
    if (identifier == null || identifier.isEmpty() || allShapes.containsKey(identifier)) {
      throw new IllegalArgumentException("invalid identifier");
    }
    IShape toAdd = ShapeFactory.create(kind, color, xSize, ySize, xCord, yCord);
    allShapes.put(identifier, toAdd);
  }

  /**
   * remove a shape from the album given a unique identifier.
   *
   * @param identifier a unique identifier for the shape.
   * @return the shape being removed, or null if the identifier do not exist.
   */
  public IShape remove(String identifier) {
    return allShapes.remove(identifier);
  }

  /**
   * reposition the item.
   *
   * @param identifier a unique identifier for the item.
   * @param x destination x coordinate.
   * @param y destination y coordinate.
   */
  public void move(String identifier, double x, double y) {
    IShape s = allShapes.get(identifier);
    if (s == null) {
      return;
    }
    s.setPosition(x, y);
  }

  /**
   * resize a shape in this album.
   *
   * @param identifier a unique identifier for the shape.
   * @param xSize the desired new x dimension (>0).
   * @param ySize the desired new y dimension (>0).
   */
  public void resize(String identifier, double xSize, double ySize) {
    IShape s = allShapes.get(identifier);
    if (s == null) {
      throw new IllegalArgumentException("Shape not found.\n");
    }
    ShapeFactory.changeSize(s, xSize, ySize);
  }

  /**
   * change the color of an existing shape given its unique identifier.
   *
   * @param identifier a unique name for the shape in this album.
   * @param colorName a string representing the name of the desired color.
   * @throws IllegalArgumentException if identifier or color name is invalid.
   */
  public void changeColor(String identifier, String colorName) throws IllegalArgumentException {
    IShape shape = this.allShapes.get(identifier);
    if (shape == null) {
      throw new IllegalArgumentException("Shape not found.\n");
    }

    ShapeFactory.changeColor(shape, colorName);
  }

  /**
   * take a snapshot of all items in the album recording their current states.
   *
   * @param note a descriptive note for this snapshot.
   */
  public void takeSnapshot(String note) {
    this.snapshots.add(new Snapshot(note, this.allShapes.values()));
  }

  /**
   * show all snapshots taken.
   * @return a String representing all snapshots.
   */
  public String showSnapshots() {
    return this.snapshots.stream().map(Snapshot::toString).collect(Collectors.joining("\n"));
  }

  /**
   * delete all snapshots.
   */
  public void resetSnapshots() {
    this.snapshots = new ArrayList<>();
  }

  /**
   * remove all items in the album; snapshots are not deleted.
   */
  public void makeEmpty() {
    allShapes = new HashMap<>();
  }
}
