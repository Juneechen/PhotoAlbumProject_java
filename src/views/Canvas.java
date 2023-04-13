package views;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class Canvas extends JPanel {
  private JLabel infoPane;
  private List<String> shapes;

  public Canvas() {
    this.setOpaque(true);
    this.setBackground(new Color(60, 120, 120));

    this.shapes = new ArrayList<>();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.PINK);
    g.fillOval(60, 60, 120, 80);

    for (String each : this.shapes) {
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


  public void renderSnapshot(List<String> shapesInfo) {
    this.shapes = new ArrayList<>();
    shapes.addAll(shapesInfo);
    this.repaint();
  }
}
