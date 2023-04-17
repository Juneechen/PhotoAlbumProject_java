package views;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.IShape;
import model.Oval;
import model.Rectangle;


public class DrawPanel extends JPanel implements IRenderer {
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

  @Override
  public void render(IShape shape) {
    this.shapes.add(shape);
  }

  @Override
  public void visit(Rectangle rect) {
    pen.setColor(rect.getColor());
    pen.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
  }

  @Override
  public void visit(Oval oval) {
    pen.setColor(oval.getColor());
    pen.fillOval(oval.getX(), oval.getY(), oval.getWidth(), oval.getHeight());
  }


  @Override
  public void reset() {
    this.shapes = new ArrayList<>();
    this.getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
  }
}
