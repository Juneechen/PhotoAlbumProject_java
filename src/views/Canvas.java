package views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;


public class Canvas extends JPanel {
  private List<String> shapesInfo;
  // private List<IShape> shapes;

  public Canvas() {
    this.setOpaque(true);
    this.setBackground(new Color(60, 120, 120));

    this.shapesInfo = new ArrayList<>();
    // this.shapes = new ArrayList<>();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.PINK);
    g.fillOval(60, 60, 120, 80);

    for (String each : this.shapesInfo) {
      Scanner scan = new Scanner(each);
      String shape = scan.next();
      int x = scan.nextInt();
      int y = scan.nextInt();
      int w = scan.nextInt();
      int h = scan.nextInt();
      int red = scan.nextInt();
      int green = scan.nextInt();
      int blue = scan.nextInt();

      g.setColor(new Color(red, green, blue));

      switch (shape) {
        case "rectangle":
          g.fillRect(x, y, w, h);
        case "oval":
          g.fillOval(x, y, w, h);
      }
    }
  }

//    @Override
//    protected void paintComponent(Graphics g) {
//      super.paintComponent(g);
//      for (IShape each : shapes) {
//        drawShape(each.copy());
//      }
//    }

  public void addShape(String info) {
    shapesInfo.add(info);
  }

//
//  public void renderSnapshot(List<String> shapesInfo) {
//    this.shapesInfo = new ArrayList<>();
//    this.shapesInfo.addAll(shapesInfo);
//    this.repaint();
//  }

//  public void renderShapes(List<IShape> shapes) {
//    this.shapes = new ArrayList<>(shapes);
//
//    this.repaint();
//  }
//
//  private void drawShape(IShape r) {
//
//  }
//
//  private void drawShape(model.Rectangle r) {
//    Graphics g = this.getGraphics();
//    g.setColor(r.getColor());
//    g.fillRect(r.getPosition().getX(), r.getPosition().getY(), r.getXDimension(), r.getYDimension());
//  }
//
//  private void drawShape(model.Oval r) {
//    Graphics g = this.getGraphics();
//    g.setColor(r.getColor());
//    g.fillOval(r.getPosition().getX(), r.getPosition().getY(), r.getXDimension(), r.getYDimension());
//  }
}
