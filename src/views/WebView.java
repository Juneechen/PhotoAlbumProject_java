package views;

import java.io.IOException;

import controller.IFeatures;
import model.IShape;
import model.Oval;
import model.Rectangle;

/**
 * a Web view for the shapeAlbum.
 * Capable of displaying a static view of all snapshots/pages from the album
 * by generating an html file with SVG markup.
 */
public class WebView implements IView {
  private final int width;
  private final int height;
  private final Appendable out;
  private final IRenderer webRenderer;
  private IFeatures f;
  private final String VER = "<!DOCTYPE html>\n";
  private final String ROOT = "<html>\n";
  private final String STYLE = "<head>\n"
                              + "<style>\n"
                              + "\t.snapshot {\n"
                              + "\t\tborder: 5px outset rgb(245, 213, 131);\n"
                              + "\t\tbackground-color: lightgrey;\n"
                              + "\t\tbox-sizing: border-box;\n"
                              + "\t}\n"
                              + "</style>\n"
                              + "</head>\n";
  private final String PAGE_END = "</body>\n</html>";
  private final String header;

  /**
   * construct a Web View with the given parameters.
   * @param header for the webpage
   * @param w width of each snapshot.
   * @param h height of each snapshot.
   * @param out an Appendable to generate the markup output.
   */
  public WebView(String header, int w, int h, Appendable out) {
    this.width = w;
    this.height = h;
    this.out = out;
    this.webRenderer = new webRender();
    this.header = "<body>\n<h1>" + header + "</h1>\n";
  }

  @Override
  public void display() {
    try {
      out.append(this.VER).append(this.ROOT).append(this.STYLE).append(this.header);
    } catch (IOException e) {
      System.out.println("append error");
    }

    while (f.hasNext()) { // request all snapshots
      f.getNext(); // it calls renderShape() for each shape in the next snapshot
    }

    // all snapshots rendered by now
    try {
      this.out.append(this.PAGE_END);
    } catch (IOException e) {
      System.out.println("append error");
    }
  }

  @Override
  public void addFeatures(IFeatures features) {
    this.f = features;
  }

  @Override
  public void updateInfoPane(String text) {
    // receive snapshot info before shapes come in
    try {
      out.append("<div class=\"snapshot\">\n\t<h2>" + text.trim() + "</h2>\n");
    } catch (IOException e) {
      System.out.println("append error");
    }
  }

  @Override
  public void renderShape(IShape shape) {
    this.webRenderer.render(shape);
  }

  @Override
  public void notifyEndOfSnapshot() {
    // called after all shapes in a snapshot are sent over for rendering
    try {
      out.append("\t</svg>\n</div>\n<p></p>\n");
    } catch (IOException e) {
      System.out.println("append error");
    }
  }

  /**
   * append info for a new snapshot image.
   */
  @Override
  public void getReadyForSnapshot() {
    // called before a new snapshot comes in
    try {
      out.append("\t<svg width=\"" + this.width + "\" height=\"" + this.height + "\">\n");
    } catch (IOException e) {
      System.out.println("append error");
    }
  }

  /**
   * a webRender takes care of rendering shapes with SVG.
   */
  private class webRender implements IRenderer {
    @Override
    public void render(IShape shape) {
      shape.accept(this);
    }

    @Override
    public void visit(Rectangle rect) {
      try {
        out.append("\t\t<rect id=\"" + rect.getName()
                + "\" x=\"" + rect.getX() + "\" y=\"" + rect.getY()
                + "\" width=\"" + rect.getWidth() + "\" height=\"" + rect.getHeight()
                + "\" fill=\"rgb("
                + rect.getColor().getRed() + ","
                + rect.getColor().getGreen() + ","
                + rect.getColor().getBlue() + ")\">\n"
                + "\t\t</rect>\n");
      } catch (IOException e) {
        System.out.println("append error");
      }
    }

    @Override
    public void visit(Oval oval) {
      try {
        out.append("\t\t<ellipse id=\"" + oval.getName()
                + "\" cx=\"" + oval.getX() + "\" cy=\"" + oval.getY()
                + "\" rx=\"" + oval.getWidth() + "\" ry=\"" + oval.getHeight()
                + "\" fill=\"rgb("
                + oval.getColor().getRed() + ","
                + oval.getColor().getGreen() + ","
                + oval.getColor().getBlue() + ")\">\n"
                + "\t\t</ellipse>\n");
      } catch (IOException e) {
        System.out.println("append error");
      }
    }
  }
}
