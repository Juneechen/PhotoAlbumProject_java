package controller;

import java.util.List;

/**
 * Features interface specifying features to be provided to an IView.
 */
public interface IFeatures {
  /**
   * exit the program.
   */
  void exit();

  /**
   * answer whether there is more snapshot to display.
   * @return true of there is more snapshot, false otherwise.
   */
  boolean hasNext();

  /**
   * request the next snapshot.
   */
  void getNext();

  /**
   * request the previous snapshot.
   */
  void getPrev();

  /**
   * request the snapshot at a given index.
   */
  void getSnapshot(int i);

  /**
   * get the list of options for a drop-down selection menu.
   * @return a list of String representing options.
   */
  List<String> getSelectionOptions();
}
