package views;

import model.IShape;
import model.Oval;
import model.Rectangle;

public interface IShapeVisitor {
  void visit(Rectangle rect);
  void visit(Oval oval);
  void visit(IShape shape);
}
