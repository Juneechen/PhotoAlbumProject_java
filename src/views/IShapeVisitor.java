package views;

import model.IShape;
import model.Oval;
import model.Rectangle;

/**
 * an IShapeVisitor interface.
 */
public interface IShapeVisitor {
  /**
   * visit a Rectangle.
   * @param rect a Rectangle.
   */
  void visit(Rectangle rect);

  /**
   * visit an Oval.
   * @param oval an Oval.
   */
  void visit(Oval oval);

  /**
   * visit an IShape.
   * @param shape an IShape of some kine.
   */
  default void visit(IShape shape) { }
}
