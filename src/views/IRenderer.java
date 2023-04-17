package views;

import model.IShape;

public interface IRenderer extends IShapeVisitor {
  void render(IShape shape);
  default void reset() { }
}
