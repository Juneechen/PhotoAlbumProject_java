package controller;

import java.awt.Color;
import java.util.List;
import java.util.Scanner;

import model.IAlbum;
import model.IShape;
import model.IShapeVisitor;
import views.IView;

public class AlbumController implements IController, Features, IShapeVisitor {
  private final IAlbum model;
  private final IView view;
  private final Readable in;
  private int iteratorIdx = 0;
  private int size;

  public AlbumController(Readable r, IAlbum model, IView view) {
    this.model = model;
    this.view = view;
    this.in = r;

    parseInput(this.in);
    this.size = model.getSnapshots().size();
    view.addFeatures(this);
  }

  @Override
  public void go() {
    if (size > 0) {
       this.getSnapshot(0); // send the first snapshot to the View
    }

    this.view.display();
  }

  @Override
  public void exit() {
    System.exit(0);
  }

  @Override
  public List<String> getSelectionOptions() {
    return model.getSnapshotIDs();
  }

  /**
   * requested by the view to retrieve a snapshot by index;
   * instead of sending the View the actual Snapshot object,
   * send over strings representing the spec of each shape contained in the snapshot.
   * @param idx
   */
  @Override
  public void getSnapshot(int idx) {
    this.iteratorIdx = idx; // keeps track of the index of the snapshot displaying
    List<IShape> shapes = model.getSnapshots().get(idx).getShapes();

    // visit, parse, and add spec of each shape to view
    for (IShape each : shapes) {
      each.accept(this);
    }

    // retrieve the snapshot id and description, ask view to update infoPane with this info.
    String snapshotInfo = model.getSnapshots().get(idx).getId() + "\n"
                          + model.getSnapshots().get(idx).getDescription();

    this.view.updateInfoPane(snapshotInfo);
  }

  /**
   * parse an IShape into a String of spec, then send it over to the View.
   * @param type type detected by the IShape visitor with the visit() method.
   * @param shape the actual shape object to parse.
   */
  private void parseShape(String type, IShape shape) {
    StringBuilder info = new StringBuilder(type + " ");

    info.append(shape.getPosition().getX() + " ").append(shape.getPosition().getY() + " ");
    info.append(shape.getXDimension() + " ").append(shape.getYDimension() + " ");
    Color c = shape.getColor();
    info.append(c.getRed() + " ").append(c.getGreen() + " ").append(c.getBlue());

    this.view.addShape(info.toString());
  }

  @Override
  public void visit(model.Rectangle rect) {
    parseShape("rectangle", rect);
  }

  @Override
  public void visit(model.Oval oval) {
    parseShape("oval", oval);
  }

  /**
   * add command components:
   * shape: Creates a new shape. Followed by these attributes:
   * ID - textual name for the shape
   * Type - type of shape (only rectangles and ovals for this assignment)
   * x position - coordinate system for both Swing and SVG starts in upper left corner
   * y position - coordinate system for both Swing and SVG starts in upper left corner
   * width - or "first dimension" like radius_x. for ovals
   * height - or "second dimension" like radius_y for ovals
   * red - RGB red value
   * green - RGB green value
   * blue - RGB blue value
   * @param info add command.
   */
  private void addShape(String[] info) {
    String id = info[1];
    String type = info[2];
    int x = Integer.parseInt(info[3]);
    int y = Integer.parseInt(info[4]);
    int w = Integer.parseInt(info[5]);
    int h = Integer.parseInt(info[6]);
    int r = Integer.parseInt(info[7]);
    int g = Integer.parseInt(info[8]);
    int b = Integer.parseInt(info[9]);

    model.add(id, type, x, y, w, h, new Color(r, g, b));
  }

  private void moveShape(String[] info) {
    String id = info[1];
    int x = Integer.parseInt(info[2]);
    int y = Integer.parseInt(info[3]);

    model.move(id, x, y);
  }

  private void colorShape(String[] info) {
    String id = info[1];
    int r = Integer.parseInt(info[2]);
    int g = Integer.parseInt(info[3]);
    int b = Integer.parseInt(info[4]);

    model.changeColor(id, new Color(r, g, b));
  }

  private void resizeShape(String[] info) {
    String id = info[1];
    int w = Integer.parseInt(info[2]);
    int h = Integer.parseInt(info[3]);

    model.resize(id, w, h);
  }

  private void removeShape(String[] info) {
    String id = info[1];
    model.remove(id);
  }

  private void snapshotCommand(String[] info) {
    StringBuilder note = new StringBuilder();

    for (int i = 1; i < info.length; i++) {
      note.append(info[i]).append(" ");
    }

    model.takeSnapshot(note.toString().trim());
  }

  private void parseInput(Readable r) {
    Scanner scan = new Scanner(r);
    String line;
    String command;

    while(scan.hasNext()) {
      line = scan.nextLine();
      String[] words = line.trim().split("\\s+");
      command = words[0].toLowerCase();

      switch (command) {
//        case "#":
//          break;
        case "shape":
          addShape(words);
          break;

        case "move":
          moveShape(words);
          break;

        case "color":
          colorShape(words);
          break;

        case "resize":
          resizeShape(words);
          break;

        case "remove":
          removeShape(words);
          break;

        case "snapshot":
          snapshotCommand(words);
          break;

        default:
          break;
      }
    }
  }
}

/**
 * Command set:
 * shape: Creates a new shape. Followed by these attributes:
 * ID - textual name for the shape
 * Type - type of shape (only rectangles and ovals for this assignment)
 * x position - coordinate system for both Swing and SVG starts in upper left corner
 * y position - coordinate system for both Swing and SVG starts in upper left corner
 * width - or "first dimension" like radius_x. for ovals
 * height - or "second dimension" like radius_y for ovals
 * red - RGB red value
 * green - RGB green value
 * blue - RGB blue value
 *
 * move: Moves a shape to a new x, y position
 * ID - text name for the shape
 * x position - coordinate system for both Swing and SVG starts in upper left corner
 * y position - coordinate system for both Swing and SVG starts in upper left corner
 *
 * color: Changes the color of a shape
 * red - RGB red value
 * green - RGB green value
 * blue - RGB blue value
 *
 * resize: Resizes the shape
 * width - or "first dimension" like radius_x. for ovals
 * height - or "second dimension" like radius_y for ovals
 *
 * remove: Removes the shape
 * ID - text name for shape to remove
 *
 * snapshot: Tells the model to take a snapshot of the current state of the album
 * description (optional) - optional text that the command file can use to tag the snapshot with extra information
 */
