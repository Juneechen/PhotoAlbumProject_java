package views;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.IShape;
import model.Oval;
import model.Rectangle;


public class DrawPanel extends JPanel implements IShapeVisitor {
  private List<IShape> shapes;
  private Graphics pen;

  public DrawPanel() {
    this.shapes = new ArrayList<>();
    repaint(); // setup pen
  }

  @Override
  protected void paintComponent(Graphics g) {
    this.pen = g;

    for (IShape each : shapes) {
      each.accept(this);
    }
  }

  public void addComponent(IShape shape) {
    this.shapes.add(shape);
  }

  private void render(Rectangle rec) {
    pen.setColor(rec.getColor());
    pen.fillRect(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
  }

  private void render(Oval oval) {
    pen.setColor(oval.getColor());
    pen.fillOval(oval.getX(), oval.getY(), oval.getWidth(), oval.getHeight());
  }

  public void reset() {
    this.shapes = new ArrayList<>();
    this.getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
  }

  @Override
  public void visit(Rectangle rect) {
    render(rect);
  }

  @Override
  public void visit(Oval oval) {
    render(oval);
  }

  @Override
  public void visit(IShape shape) { }
}
