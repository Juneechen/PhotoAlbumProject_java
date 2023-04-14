package mvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.AlbumController;
import controller.IController;
import model.IAlbum;
import model.ShapesAlbum;
import utilities.CommandReader;
import views.IView;
import views.SwingView;

public class GraphicAlbum {
  public static void main(String[] args) {
    IAlbum model = new ShapesAlbum();
    IView view = new SwingView("Photo Album");

    // TODO: change to taking command line args
    try {
      File file = new File("buildings.txt");
      FileReader reader = new FileReader(file);

      CommandReader.setupAlbum(model, reader); // take the pre-set command from an input source

      IController controller = new AlbumController(model, view);
      controller.go();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
