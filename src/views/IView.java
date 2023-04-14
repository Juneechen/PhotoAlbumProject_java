package views;

import controller.Features;
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
   */
  void resetFocus();

  /**
   * implement features by connecting buttons or key with the given set of features (actions).
   * @param features
   */
  void addFeatures(Features features);

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
   * @param msg text to display.
   */
  void showPopUp(String msg);

  /**
   * called by Controller after sending over a new snapshot to render.
   */
  void refresh();

  /**
   * clear graphic panel components and all renderings.
   */
  void clear();
}
