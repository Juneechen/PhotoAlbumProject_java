package controller;

import java.util.List;

import model.IAlbum;
import model.IShape;
import views.IView;

/**
 * an AlbumController communicates between the view and the model
 * for displaying snapshot in the model through the view.
 */
public class AlbumController implements IController, IFeatures {
  private final IAlbum model;
  private final IView view;
  private int iteratorIdx = -1;
  private int albumSize;

  /**
   * construct an AlbumController object with the given model and view.
   * @param model a photoAlbum model with a collection of snapshot.
   * @param view a view to display snapshots.
   */
  public AlbumController(IAlbum model, IView view) {
    this.model = model;
    this.view = view;
    this.albumSize = model.getTotalSnapshots();
  }

  @Override
  public void go() {
    this.albumSize = model.getTotalSnapshots();
    this.view.addFeatures(this); // need a correct album size before initializing selection menu

    if (albumSize > 0) {
      this.getSnapshot(0); // send the first snapshot to the View
    }

    this.view.display();
    // got everything, exit
    System.out.println(" ---- got back from webView display, return\n\n");
  }

  @Override
  public List<String> getSelectionOptions() {
    return this.model.getSnapshotIDs();
  }

  /**
   * requested by the view to retrieve a snapshot by index;
   * instead of sending the View the actual Snapshot object,
   * send over strings representing the spec of each shape contained in the snapshot.
   * @param idx guaranteed to be a valid index.
   */
  @Override
  public void getSnapshot(int idx) {
    this.iteratorIdx = idx; // update to keep track of the index of the snapshot displaying

    // retrieve the snapshot id and description
    String snapshotInfo = model.getSnapshotAt(idx).getId() + "\n"
            + model.getSnapshotAt(idx).getDescription();
    this.view.updateInfoPane(snapshotInfo); // ask view to update infoPane with this info

    this.view.clear(); // notify the view shapes in the snapshot is about to be sent over.

    List<IShape> shapes = model.getSnapshotAt(idx).getShapes();
    for (IShape each : shapes) {
      this.view.renderShape(each);  // ask view to render each shape
    }

    this.view.refresh(); // notify the view all shapes in the snapshot has been sent over.
    this.view.resetFocus();
  }

  @Override
  public boolean hasNext() {
    int nextInd = iteratorIdx + 1;
    return nextInd < albumSize;
  }

  @Override
  public void getNext() {
    if (this.hasNext()) {
      this.getSnapshot(iteratorIdx + 1); // this method updates iteratorIdx with the given value
    } else {
      this.view.showPopUp("End of the photo album. No snapshots to show beyond this one.");
    }
    this.view.resetFocus();
  }

  @Override
  public void getPrev() {
    if (iteratorIdx > 0) {
      this.getSnapshot(iteratorIdx - 1); // this method updates iteratorIdx with the given value
    } else {
      this.view.showPopUp("Start of the photo album. No snapshots to show before this one.");
    }
    this.view.resetFocus();
  }

  @Override
  public void exit() {
    System.exit(0);
  }
}
