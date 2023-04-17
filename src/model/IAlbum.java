package model;

import java.awt.Color;
import java.util.List;

/**
 * An IAlbum interface.
 */
public interface IAlbum {
  /**
   * answer the total number of snapshots in the album.
   * @return the number of snapshots.
   */
  int getTotalSnapshots();

  /**
   * add a shape into the album given a unique identifier and a set of shape properties.
   *
   * @param name a unique name for the shape.
   * @param kind type of the shape.
   * @param color desired fill color.
   * @param width x dimension of the shape; could be width or x radius.
   * @param height y dimension of the shape; could be height or y radius.
   * @param xCord x coordinate to place the shape.
   * @param yCord y coordinate to place the shape.
   */
  void add(String name, String kind, int xCord, int yCord,
           int width, int height, Color color);

  /**
   * remove an object into the album along given a unique identifier.
   *
   * @param identifier a unique identifier for the item.
   * @return IShape the object being removed, or null if the identifier do not exist.
   */
  IShape remove(String identifier);

  /**
   * reposition the item.
   *
   * @param identifier a unique identifier for the item.
   * @param x destination x coordinate.
   * @param y destination y coordinate.
   */
  void move(String identifier, int x, int y);

  /**
   * resize a shape in this album.
   *
   * @param identifier a unique identifier for the shape.
   * @param xSize the desired new x dimension (>0).
   * @param ySize the desired new y dimension (>0).
   */
  void resize(String identifier, int xSize, int ySize);

  /**
   * change the color of an existing shape given its unique identifier.
   *
   * @param identifier a unique name for the shape in this album.
   * @param colorName a string representing the name of the desired color.
   * @throws IllegalArgumentException if identifier or color name is invalid.
   */
  void changeColor(String identifier, Color colorName);

  /**
   * take a snapshot of all items in the album recording their current states.
   *
   * @param note a descriptive note for this snapshot.
   */
  void takeSnapshot(String note);

  /**
   * get a snapshot by index; snapshots are ordered by time taken.
   * @param idx the index of the snapshot.
   * @return the requested snapshot, or null if index out of bound.
   */
  Snapshot getSnapshotAt(int idx);

  /**
   * show all snapshots taken.
   * @return a String representing all snapshots.
   */
  String showSnapshots();

  /**
   * retrieve all snapshots taken.
   * @return a unmodifiable list of all snapshots.
   */
  List<Snapshot> getSnapshots();

  /**
   * retrieve the ID of all snapshots taken.
   * @return a list of all snapshots' ID.
   */
  List<String> getSnapshotIDs();

//  /**
//   * retrieve a snapshot with its ID.
//   * @return the snapshot being requested if found, null otherwise.
//   */
//  Snapshot getSnapshotWithID(String id);

  /**
   * delete all snapshots.
   */
  void resetSnapshots();

  /**
   * remove all items in the album; snapshots are not deleted.
   */
  void makeEmpty();
}
