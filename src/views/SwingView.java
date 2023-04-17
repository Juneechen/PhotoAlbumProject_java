package views;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import controller.IFeatures;
import model.IShape;
import model.Oval;
import model.Rectangle;

/**
 * a Swing view for the shapeAlbum. Capable of displaying snapshots/pages from the album.
 */
public class SwingView extends JFrame implements IView {
  public final int canvasWidth;
  public final int canvasHeight;
  private final DrawPanel graphicPane = new DrawPanel();
  private final JButton prev = new JButton("previous (p)");
  private final JButton next = new JButton("next (n)");
  private final JButton quit = new JButton("quit (q)");
  private final JButton select = new JButton("select (s)");
  private JLabel infoPane;
  private JComboBox<String> dropdownMenu = new JComboBox<>();

  /**
   * construct a Swing View with the given parameters.
   * @param title of the main window.
   * @param w width of the graphic area.
   * @param h height of the graphic area.
   */
  public SwingView(String title, int w, int h) {
    super(title); // set the tile of this frame
    this.canvasWidth = w;
    this.canvasHeight = h;

    setUpPane();
    pack(); // layout setup to fit all components
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * set up the look and feel of all components in this frame:
   * - an info panel at the top, for displaying snapshot id and description;
   * - a graphic panel at the center, for rendering and displaying snapshot.
   * - buttons;
   * - a panel for buttons;
   * - pop-up selection box from buttons.
   */
  private void setUpPane() {
    // setup buttons pane and add buttons to it
    JPanel buttonsPane = new JPanel(new FlowLayout());
    buttonsPane.add(prev);
    buttonsPane.add(next);
    buttonsPane.add(select);
    buttonsPane.add(quit);

    // setup info pane shown on the top for snapshot info
    infoPane = new JLabel("snapshot info");
    infoPane.setBorder(BorderFactory.createEmptyBorder(5, 7, 5, 7));
    infoPane.setOpaque(true);
    infoPane.setBackground(new Color(245, 213, 131));
    infoPane.setPreferredSize(new Dimension(canvasWidth, 45));

    // setup graphic pane for displaying snapshot
    this.graphicPane.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

    // add the above components to default panel / main window
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
  public void renderShape(IShape shape) {
    this.graphicPane.render(shape); // add to graphic pane's component for repaint
  }

  @Override
  public void updateInfoPane(String text) {
    text = "<html>" + text + "<html>";
    text = text.replaceAll("\n", "<br>"); // to make new line show up properly
    this.infoPane.setText(text);
  }


  @Override
  public void showPopUp(String msg) {
    JOptionPane.showMessageDialog(SwingView.this, msg);
  }

  /**
   * show a popup dialog with the given title message and the given component in it.
   * @param title of the dialog box.
   * @param component contained in the dialog box.
   */
  private void showPopUpWithComponent(String title, JComponent component) {
    JOptionPane.showMessageDialog(SwingView.this, component, title, JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void addFeatures(IFeatures features) {
    prev.addActionListener(l -> features.getPrev());
    next.addActionListener(l -> features.getNext());
    quit.addActionListener(l -> features.exit());

    // request from Controller the list of drop-down options
    String[] selections = features.getSelectionOptions().toArray(new String[0]);
    dropdownMenu = new JComboBox<>(selections); // make selection box and put in options

    // on-selection action: request snapshot from Controller, then repaint
    dropdownMenu.addActionListener(l -> features.getSnapshot(dropdownMenu.getSelectedIndex()));

    // select button action: bring up a pop-up dialog, with the selection box in it
    select.addActionListener(l -> showPopUpWithComponent("Select a snapshot", dropdownMenu));

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
                  features.getPrev();
                }
                if (e.getKeyCode()==KeyEvent.VK_N) {
                  features.getNext();
                }
                if (e.getKeyCode()==KeyEvent.VK_S) {
                  showPopUpWithComponent("Select a snapshot", dropdownMenu);
                }
              }

              @Override
              public void keyReleased(KeyEvent e) { }
            }
    );
  }

  /**
   * clear graphic panel components and all renderings.
   */
  @Override
  public void clear() {
    this.graphicPane.reset();
  }

  @Override
  public void refresh() {
    this.graphicPane.repaint();
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * an inner class DrawPanel that takes care of rendering shapes for SwingView.
   */
  class DrawPanel extends JPanel implements IRenderer {
    private List<IShape> shapes;
    private Graphics pen;

    public DrawPanel() {
      this.shapes = new ArrayList<>();
      repaint(); // setup pen, calls paintComponent()
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
}
