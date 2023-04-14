package views;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.Features;

public class SwingView extends JFrame implements IView {
  public static final int WIDTH = 800;
  public static final int HEIGHT = 800;
  private JLabel infoPane;
  private Canvas graphicPane;
  private JPanel buttonsPane;
  private JButton prev;
  private JButton next;
  private JButton select;
  private JComboBox<String> selectionBox = new JComboBox<>();
  private JButton quit;

  public SwingView(String title) {
    super(title); // set the tile of this frame
    setUpPane();
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * set up the look and feel of all components in this frame:
   * - an info panel at the top, for displaying snapshot id and description;
   * - a graphic panel at the center, for rendering and displaying snapshot.
   * - buttons;
   * - a panel for buttons;
   * - pop-up selection box from buttons.
   *
   */
  private void setUpPane() {
    // make buttons
    prev = new JButton("previous");
    next = new JButton("next");
    select = new JButton("select");
    quit = new JButton("quit");

    // setup buttons pane and add buttons to it
    buttonsPane = new JPanel(new FlowLayout());
    buttonsPane.add(prev);
    buttonsPane.add(next);
    buttonsPane.add(select);
    buttonsPane.add(quit);

    // setup info pane shown on the top for snapshot info
    infoPane = new JLabel("snapshot info");
    infoPane.setBorder(BorderFactory.createEmptyBorder(5, 7, 5, 7));
    infoPane.setOpaque(true);
    infoPane.setBackground(new Color(245, 213, 131));
    infoPane.setPreferredSize(new Dimension(WIDTH, 45));

    // setup graphic pane for displaying snapshot
    graphicPane = new Canvas();
    this.graphicPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    // add the above components to default panel
    this.add(infoPane, BorderLayout.PAGE_START);
    this.add(graphicPane, BorderLayout.CENTER);
    this.add(buttonsPane, BorderLayout.PAGE_END);
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
  public void addShape(String info) {
    graphicPane.addShape(info);
  }

  @Override
  public void showPopUpMsg(String msg) {
    JOptionPane.showMessageDialog(SwingView.this, msg);
  }

  private void showPopUpDialogWithComponent(String title, JComponent component) {
    JOptionPane.showMessageDialog(SwingView.this, component, title, JOptionPane.PLAIN_MESSAGE);
    component.requestFocus();
  }

  @Override
  public void updateInfoPane(String text) {
    text = "<html>" + text + "<html>";
    text = text.replaceAll("\n", "<br>");
    this.infoPane.setText(text);
  }

  @Override
  public void refresh() {
    graphicPane.repaint();
  }

  @Override
  public void addFeatures(Features features) {
    quit.addActionListener(l -> features.exit());

    // request from controller the list of options for selection menu
    String[] selections = features.getSelectionOptions().toArray(new String[0]);
    selectionBox = new JComboBox<>(selections); // make selection box and put in options

    // add on-selection action:
    // request snapshot info from Controller, then repaint all
    selectionBox.addActionListener(l -> features.getSnapshot(selectionBox.getSelectedIndex()));

    // add select button action: bring up a message dialog, with the selection box in it
    select.addActionListener(l -> showPopUpDialogWithComponent("Select a snapshot", selectionBox));

    // add prev and next button action:
    prev.addActionListener(l -> features.getPrev());
    next.addActionListener(l -> features.getNext());

    // add key-binding for all buttons
    this.addKeyListener(
            new KeyListener() {
              @Override
              public void keyTyped(KeyEvent e) { }

              @Override
              public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_Q) {
                  features.exit();
                }
                if (e.getKeyCode()==KeyEvent.VK_P) {
                  System.out.println("P pressed");
                  features.getPrev();
                }
                if (e.getKeyCode()==KeyEvent.VK_N) {
                  System.out.println("N pressed");
                  features.getNext();
                }
                if (e.getKeyCode()==KeyEvent.VK_S) {
                  showPopUpDialogWithComponent("Select a snapshot", selectionBox);
                }
              }

              @Override
              public void keyReleased(KeyEvent e) { }
            }
    );
  }

  @Override
  public void clear() {
    this.graphicPane.reset();
    //graphicPane.getGraphics().clearRect(0, 0, graphicPane.getWidth(), graphicPane.getHeight());
  }
}
