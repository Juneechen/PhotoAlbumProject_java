package views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;


public class Canvas extends JPanel {
  private List<String> shapesInfo;

  public Canvas() {
    this.shapesInfo = new ArrayList<>();
  }

  @Override
  protected void paintComponent(Graphics g) {
    // super.paintComponent(g);

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

  public void reset() {
    this.shapesInfo = new ArrayList<>();
  }

  public void addShape(String info) {
    shapesInfo.add(info);
  }
}
