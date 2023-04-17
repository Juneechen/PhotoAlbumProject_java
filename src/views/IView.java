package views;

import controller.IFeatures;
import model.IShape;

/**
 * an IView interface specifying functionalities any IView for a shapeAlbum should have.
 */
public interface IView {
  /**
   * make the view an all initial components in the graphic panel visible.
   */
  void display();

  /**
   * make focusable and request focus.
   * do nothing on default for views that aren't focusable.
   */
  default void resetFocus() { }

  /**
   * implement features by connecting buttons or key with the given set of features (actions).
   * @param features
   */
  void addFeatures(IFeatures features);

  /**
   * update info pane with the given text.
   * @param text info to display.
   */
  void updateInfoPane(String text);

  /**
   * render a shape.
   * @param shape the shape to render.
   */
  void renderShape(IShape shape);

  /**
   * bring up a popup dialog box with the given message.
   * do nothing on default for views that aren't able to do so.
   *
   * @param msg text to display.
   */
  default void showPopUp(String msg) { }

  /**
   * called by Controller when done sending over a snapshot to render.
   */
  void notifyEndOfSnapshot();

  /**
   * called by Controller before sending over a new snapshot to render.
   */
  void getReadyForSnapshot();
}
