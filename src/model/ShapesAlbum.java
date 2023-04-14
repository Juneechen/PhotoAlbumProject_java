package model;

import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A Shapes Album class representing a simple "photo album" from shapes.
 */
public class ShapesAlbum implements IAlbum {
  private Map<String, IShape> shapeMap = new HashMap<>();
  // private Map<String, Snapshot> snapshotMap = new HashMap<>();
  private List<IShape> allShapes = new ArrayList<>();
  private List<Snapshot> snapshots = new ArrayList<>();

  /**
   * add a shape into the album given a unique identifier and a set of shape properties.
   *
   * @param identifier a unique name for the shape.
   * @param kind type of the shape.
   * @param xCord x coordinate to place the shape.
   * @param yCord y coordinate to place the shape.
   * @param width x dimension of the shape; could be width or x radius.
   * @param height y dimension of the shape; could be height or y radius.
   * @param color desired fill color.
   */
  @Override
  public void add(String identifier, String kind, int xCord, int yCord,
                  int width, int height, Color color) {
    if (identifier == null || identifier.isEmpty() || shapeMap.containsKey(identifier)) {
      throw new IllegalArgumentException("invalid identifier");
    }
    try {
      IShape toAdd = ShapeFactory.create(identifier, kind, xCord, yCord, width, height, color);
      shapeMap.put(identifier, toAdd);
      allShapes.add(toAdd);
    }
    catch (IllegalArgumentException e) {
      return;
    }
  }

  /**
   * remove a shape from the album given a unique identifier.
   *
   * @param identifier a unique identifier for the shape.
   * @return the shape being removed, or null if the identifier do not exist.
   */
  @Override
  public IShape remove(String identifier) {
    IShape target = shapeMap.remove(identifier);
    if (target != null) {
      allShapes.remove(target);
    }
    return target;
  }

  /**
   * reposition the item.
   *
   * @param identifier a unique identifier for the item.
   * @param x destination x coordinate.
   * @param y destination y coordinate.
   */
  @Override
  public void move(String identifier, int x, int y) {
    IShape s = shapeMap.get(identifier);
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
  @Override
  public void resize(String identifier, int xSize, int ySize) {
    IShape s = shapeMap.get(identifier);
    if (s == null) {
      return;
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
  @Override
  public void changeColor(String identifier, Color colorName) throws IllegalArgumentException {
    IShape shape = this.shapeMap.get(identifier);
    if (shape == null) {
      return;
    }

    ShapeFactory.changeColor(shape, colorName);
  }

  /**
   * take a snapshot of all items in the album recording their current states.
   *
   * @param note a descriptive note for this snapshot.
   */
  @Override
  public void takeSnapshot(String note) {
    List<IShape> temp = new ArrayList<>();
    this.allShapes.forEach(s -> temp.add(s.copy()));

    Snapshot toAdd = new Snapshot(note, temp);
    this.snapshots.add(toAdd);
    // this.snapshotMap.put(toAdd.getId(), toAdd);
  }

  /**
   * show all snapshots taken.
   * @return a String representing all snapshots.
   */
  @Override
  public String showSnapshots() {
    return "Printing Snapshots\n"
            + this.snapshots.stream().map(Snapshot::toString).collect(Collectors.joining("\n"));
  }

  @Override
  public List<Snapshot> getSnapshots() {
    return Collections.unmodifiableList(snapshots);
  }

  @Override
  public List<String> getSnapshotIDs() {
    return snapshots.stream().map(Snapshot::getId).collect(Collectors.toList());
  }
//
//  @Override
//  public Snapshot getSnapshotWithID(String id) {
//    return snapshotMap.get(id);
//  }

  /**
   * delete all snapshots.
   */
  @Override
  public void resetSnapshots() {
    this.snapshots = new ArrayList<>();
  }

  /**
   * remove all items in the album; snapshots are not deleted.
   */
  @Override
  public void makeEmpty() {
    shapeMap = new HashMap<>();
    allShapes = new ArrayList<>();
  }

  /**
   * a text representation of all shapes in the album.
   * @return a text description of all shapes.
   */
  @Override
  public String toString() {
    return this.allShapes.stream().map(IShape::toString).collect(Collectors.joining("\n\n"));
  }
}
