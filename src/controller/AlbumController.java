package controller;

import java.util.List;

import model.IAlbum;
import model.IShape;
import views.IView;

public class AlbumController implements IController, Features {
  private final IAlbum model;
  private final IView view;
  private int iteratorIdx = -1;
  private int albumSize;

  public AlbumController(IAlbum model, IView view) {
    this.model = model;
    this.view = view;
    this.albumSize = model.getSnapshots().size();
  }

  @Override
  public void go() {
    this.albumSize = model.getSnapshots().size();
    this.view.addFeatures(this); // must have a correct album size for initializing selection menu

    if (albumSize > 0) {
      this.getSnapshot(0); // send the first snapshot to the View
    }

    this.view.display();
  }

  @Override
  public List<String> getSelectionOptions() {
    return this.model.getSnapshotIDs();
  }

  /**
   * requested by the view to retrieve a snapshot by index;
   * instead of sending the View the actual Snapshot object,
   * send over strings representing the spec of each shape contained in the snapshot.
   * @param idx
   */
  @Override
  public void getSnapshot(int idx) {
    this.iteratorIdx = idx; // update to keep track of the index of the snapshot displaying
    this.view.clear(); // clear the View before sending over new shapes to display

    List<IShape> shapes = model.getSnapshots().get(idx).getShapes();

    for (IShape each : shapes) {
      this.view.renderShape(each);  // add to view's component
    }
    this.view.refresh(); // repaint all

    // retrieve the snapshot id and description
    String snapshotInfo = model.getSnapshots().get(idx).getId() + "\n"
            + model.getSnapshots().get(idx).getDescription();
    this.view.updateInfoPane(snapshotInfo); // ask view to update infoPane with this info

    this.view.resetFocus();
  }

  @Override
  public void getNext() {
    int nextInd = iteratorIdx + 1;
    if (nextInd < albumSize) {
      this.getSnapshot(nextInd); // this method updates iteratorIdx with the given value
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
