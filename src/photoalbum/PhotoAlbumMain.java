package photoalbum;

import controller.AlbumController;
import controller.IController;
import model.IAlbum;
import model.ShapesAlbum;
import utilities.ArgsReader;
import utilities.CommandReader;
import views.IView;
import views.SwingView;
import views.WebView;

/**
 * Photo Album Main Entry.
 */
public class PhotoAlbumMain {

  /**
   * take command line arguments and set up model, view, controller accordingly.
   * @param args specifying input source, view, optional output source, and dimensions.
   */
  public static void main(String[] args) throws Exception {
//    args = new String[] {"-in", "buildings.txt", "-view", "web", "-out", "buildingsOut.html", "800", "800"};
//    args = new String[] {"-in", "buildings.txt", "-view", "graphical", "800", "800"};

    ArgsReader r = new ArgsReader(args); // parse arguments and check validity

    // set up view:
    IView view = null;
    switch (r.getView().toLowerCase()) {
      case "graphical":
        view = new SwingView("Photo Album GUI", r.getWidth(), r.getHeight());
        break;

      case "web":
        view = new WebView("Photo Album Web View", r.getWidth(), r.getHeight(), r.getAppendable());
        break;

      default:
        System.out.println("Unknown type of view");
        System.exit(0);
    }

    // set up model with a Readable command source
    IAlbum model = new ShapesAlbum();
    CommandReader.setupAlbum(model, r.getReadable()); // take command from an input source
    r.closeReadable();

    // set up Controller with the view and model
    IController controller = new AlbumController(model, view);
    controller.go(); // activate the controller

    if (r.getView().equalsIgnoreCase("web")) {
      r.closeAppendable(); // need to close file to flush data into disk from memory
    }
  }
}
