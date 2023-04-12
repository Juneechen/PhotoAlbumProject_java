package views;

import java.awt.*;

import javax.swing.*;

import controller.Features;

public class SwingView extends JFrame implements IView {
  public static final int WIDTH = 300;
  public static final int HEIGHT = 200;
  private JButton prev;
  private JButton next;
  private JButton select;
  private JButton quit;

  public SwingView(String title) {
    super(title);
    super.setSize(WIDTH, HEIGHT);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());

    prev = new JButton("previous");
    next = new JButton("next");
    select = new JButton("select");
    quit = new JButton("quit");

    add(prev);
    add(next);
    add(select);
    add(quit);
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void addFeatures(Features features) {
    quit.addActionListener(l -> features.exit());
  }
}
