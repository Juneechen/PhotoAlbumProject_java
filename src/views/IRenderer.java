package views;

import model.IShape;

/**
 * IRenderer interface for rendering IShape.
 */
public interface IRenderer extends IShapeVisitor {
  /**
   * render an IShape.
   * @param shape an IShape.
   */
  void render(IShape shape);

  /**
   * reset renderings.
   */
  default void reset() { }
}
