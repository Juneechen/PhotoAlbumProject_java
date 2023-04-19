package model;

import java.util.List;

/**
 * an ISnapshot interface.
 */
public interface ISnapshot {
  /**
   * id getter.
   * @return the id.
   */
  String getId();

  /**
   * description getter.
   * @return the description.
   */
  String getDescription();

  /**
   * retrieve a list of all shapes contained in one snapshot.
   * @return an unmodifiable list of all shapes.
   */
  List<IShape> getShapes();
}
