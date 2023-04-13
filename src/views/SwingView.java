package views;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.*;

import controller.Features;

public class SwingView extends JFrame implements IView {
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  private JLabel infoPane;
  private Canvas graphicPane;
  private JPanel buttonsPane;
  private JButton prev;
  private JButton next;
  private JButton select;
  private JButton quit;
  private JButton testclear;
  private JButton testdrawOval;

  public SwingView(String title) {
    super(title);
    super.setSize(WIDTH, HEIGHT);
    setUpPane();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // pack();
  }

  private void setUpPane() {
    // make buttons
    prev = new JButton("previous");
    next = new JButton("next");
    select = new JButton("select");
    quit = new JButton("quit");
    // TODO: delete test items
    testclear = new JButton("test clear");
    testdrawOval = new JButton("test draw oval");


    // make buttons pane and add buttons
    buttonsPane = new JPanel(new FlowLayout());
    buttonsPane.add(prev);
    buttonsPane.add(next);
    buttonsPane.add(select);
    buttonsPane.add(quit);
    // TODO: delete test items
    buttonsPane.add(testclear);
    buttonsPane.add(testdrawOval);


    // make info pane shown on the top for snapshot info
    infoPane = new JLabel("snapshot info");
//     infoPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    infoPane.setBorder(BorderFactory.createMatteBorder(10, 15, 10, 15, new Color(160, 200, 160)));
    infoPane.setOpaque(true);
    infoPane.setBackground(new Color(248, 213, 131));
    infoPane.setPreferredSize(new Dimension(WIDTH, 50));

    // make graphic pane for displaying snapshot
    graphicPane = new Canvas();
//    graphicPane = new JPanel() {
//      @Override
//      protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.PINK);
//        g.fillOval(20, 20, 120, 80);
//      }
//    };

//    graphicPane.setOpaque(true);
//    graphicPane.setBackground(new Color(60, 120, 120));
    // graphicPane.setPreferredSize(new Dimension(WIDTH, HEIGHT-150));

    // add the above components to default panel
    this.add(infoPane, BorderLayout.PAGE_START);
    this.add(graphicPane, BorderLayout.CENTER);
    this.add(buttonsPane, BorderLayout.PAGE_END);
  }

  @Override
  public void showSnapshot(List<String> info) {
    this.graphicPane.renderSnapshot(info);
  }

  @Override
  public void display() {
    this.setVisible(true);
    this.requestFocus();
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void addFeatures(Features features) {
    quit.addActionListener(l -> features.exit());
    // TODO: delete test items
    testclear.addActionListener(l -> this.clearPane());
    testdrawOval.addActionListener(l -> this.drawOval());


    this.addKeyListener(
            new KeyListener() {
              @Override
              public void keyTyped(KeyEvent e) {
//                if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
//                  features.exit();
//                }
              }

              @Override
              public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_Q) {
                  features.exit();
                }
              }

              @Override
              public void keyReleased(KeyEvent e) {

              }
            }
    );
  }

  public void clearPane() {
    graphicPane.getGraphics().clearRect(0, 0, graphicPane.getWidth(), graphicPane.getHeight());
  }

  public void drawOval() {
    Graphics g = graphicPane.getGraphics();
    g.setColor(Color.PINK);
    g.fillOval(20, 20, 120, 80);
  }
}
