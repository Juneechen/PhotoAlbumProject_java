package mvc;

import java.awt.image.DataBufferDouble;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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

    // TODO: change to taking command line args
    try {
      File file = new File("demo_input.txt");
      FileReader reader = new FileReader(file);

      IController controller = new AlbumController(reader, model, view);
      controller.go();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}
