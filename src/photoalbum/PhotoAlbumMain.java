package photoalbum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.AlbumController;
import controller.IController;
import model.IAlbum;
import model.ShapesAlbum;
import utilities.ArgsReader;
import utilities.CommandReader;
import views.IView;
import views.SwingView;

/**
 * Photo Album Main Entry class.
 */
public class PhotoAlbumMain {

  /**
   * take command line arguments and set up model, view, controller accordingly.
   * @param args
   */
  public static void main(String[] args) {
    ArgsReader r = new ArgsReader(args); // parse arguments and check validity

    IAlbum model = new ShapesAlbum();
    IView view;

    if (r.getView().equalsIgnoreCase("graphical")) {
      view = new SwingView("Photo Album", r.getWidth(), r.getHeight());
    } else {
      view = new SwingView("Photo Album", r.getWidth(), r.getHeight());
    }

    try {
      File file = new File(r.getInFile());
      FileReader reader = new FileReader(file);

      CommandReader.setupAlbum(model, reader); // take the pre-set command from an input source

      IController controller = new AlbumController(model, view);
      controller.go();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
