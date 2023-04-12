package mvc;

import controller.AlbumController;
import controller.IController;
import model.IAlbum;
import model.ShapesAlbum;
import views.IView;
import views.SwingView;

public class GraphicAlbum {
  public static void main(String[] args) {
    IAlbum model = new ShapesAlbum();
    IView view = new SwingView("Photo Album");
    IController controller = new AlbumController(model, view);

    controller.go();
  }
}
