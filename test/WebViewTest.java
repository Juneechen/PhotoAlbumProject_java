import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import controller.AlbumController;
import controller.IFeatures;
import java.awt.Color;
import model.IAlbum;
import model.IShape;
import model.Oval;
import model.Rectangle;
import model.ShapesAlbum;
import views.IView;
import views.WebView;

/**
 * unit tests for web view.
 */
public class WebViewTest {
  private final IAlbum model = new ShapesAlbum();
  private final StringBuffer output = new StringBuffer();
  private final IView view = new WebView("Web View", 800, 800, output);
  private final IFeatures features = new AlbumController(model, view);

  /**
   * setup features for testing.
   * @throws Exception for any error.
   */
  @Before
  public void setUp() throws Exception {
    this.view.addFeatures(features);
  }

  /**
   * test displaying an empty model; should append the default start and end of an HTML view.
   */
  @Test
  public void testDisplayEmpty() {
    this.view.display();
    assertEquals("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "\t.snapshot {\n" +
            "\t\tborder: 5px outset rgb(245, 213, 131);\n" +
            "\t\tbackground-color: lightgrey;\n" +
            "\t\tbox-sizing: border-box;\n" +
            "\t}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Web View</h1>\n" +
            "</body>\n" +
            "</html>", output.toString());
  }

  /**
   * test updateInfoPane(): should append markup for the start of a snapshot and its header.
   */
  @Test
  public void updateInfoPane() {
    view.updateInfoPane("snapshot ID and description");
    assertEquals("<div class=\"snapshot\">\n\t<h2>snapshot ID and description</h2>\n",
            output.toString());
  }

  /**
   * test renderShape() on Rectangle.
   */
  @Test
  public void testRenderShapeRect() {
    IShape rect = new Rectangle("R", 0, 0, 100, 100, Color.WHITE);
    view.renderShape(rect);
    assertEquals("\t\t<rect id=\"R\" x=\"0\" y=\"0\" width=\"100\" height=\"100\" " +
            "fill=\"rgb(255,255,255)\">\n\t\t</rect>\n", output.toString());
  }

  /**
   * test renderShape() on Oval.
   */
  @Test
  public void testRenderShapeOval() {
    IShape rect = new Oval("O", 0, 0, 100, 100, Color.WHITE);
    view.renderShape(rect);
    assertEquals("\t\t<ellipse id=\"O\" cx=\"0\" cy=\"0\" rx=\"100\" ry=\"100\" "
            + "fill=\"rgb(255,255,255)\">\n\t\t</ellipse>\n", output.toString());
  }

  /**
   * test notifyEndOfSnapshot(); should append markup representing the end of an SVG image.
   */
  @Test
  public void testNotifyEndOfSnapshot() {
    view.notifyEndOfSnapshot();
    assertEquals("\t</svg>\n</div>\n<p></p>\n", output.toString());
  }

  /**
   * test getReadyForSnapshot(); should append markup representing the start of an SVG image.
   */
  @Test
  public void testGetReadyForSnapshot() {
    view.getReadyForSnapshot();
    assertEquals("\t<svg width=\"800\" height=\"800\">\n", output.toString());
  }
}