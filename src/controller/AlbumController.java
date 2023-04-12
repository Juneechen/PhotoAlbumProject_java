package controller;

import model.IAlbum;
import views.IView;

public class AlbumController implements IController, Features {
  private IAlbum model;
  private IView view;

  public AlbumController(IAlbum model, IView view) {
    this.model = model;
    this.view = view;
    view.addFeatures(this);
  }

  @Override
  public void go() {
    this.view.display();
  }

  @Override
  public void exit() {
    System.exit(0);
  }
}
