package controller;

import model.Oval;
import model.Rectangle;

public interface IShapeVisitor {
  void visit(Rectangle rect);
  void visit(Oval oval);
}
