package utilities;

import java.awt.*;
import java.util.Scanner;

import model.IAlbum;

public class CommandReader {
  public static void setupAlbum(IAlbum album, Readable in) {
    Scanner scan = new Scanner(in);
    String line;
    String command;

    while(scan.hasNext()) {
      line = scan.nextLine();
      String[] spec = line.trim().split("\\s+");
      command = spec[0].toLowerCase();

      switch (command) {
        case "shape":
          addShape(album, spec);
          break;

        case "move":
          moveShape(album, spec);
          break;

        case "color":
          colorShape(album, spec);
          break;

        case "resize":
          resizeShape(album, spec);
          break;

        case "remove":
          removeShape(album, spec);
          break;

        case "snapshot":
          snapshotCmd(album, spec);
          break;

        default:
          break;
      }
    }
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
  private static void addShape(IAlbum album, String[] info) {
    String id = info[1];
    String type = info[2];
    int x = Integer.parseInt(info[3]);
    int y = Integer.parseInt(info[4]);
    int w = Integer.parseInt(info[5]);
    int h = Integer.parseInt(info[6]);
    int r = Integer.parseInt(info[7]);
    int g = Integer.parseInt(info[8]);
    int b = Integer.parseInt(info[9]);

    album.add(id, type, x, y, w, h, new Color(r, g, b));
  }

  private static void moveShape(IAlbum album, String[] info) {
    String id = info[1];
    int x = Integer.parseInt(info[2]);
    int y = Integer.parseInt(info[3]);

    album.move(id, x, y);
  }

  private static void colorShape(IAlbum album, String[] info) {
    String id = info[1];
    int r = Integer.parseInt(info[2]);
    int g = Integer.parseInt(info[3]);
    int b = Integer.parseInt(info[4]);

    album.changeColor(id, new Color(r, g, b));
  }

  private static void resizeShape(IAlbum album, String[] info) {
    String id = info[1];
    int w = Integer.parseInt(info[2]);
    int h = Integer.parseInt(info[3]);

    album.resize(id, w, h);
  }

  private static void removeShape(IAlbum album, String[] info) {
    String id = info[1];
    album.remove(id);
  }

  private static void snapshotCmd(IAlbum album, String[] info) {
    StringBuilder note = new StringBuilder();

    for (int i = 1; i < info.length; i++) {
      note.append(info[i]).append(" ");
    }

    album.takeSnapshot(note.toString().trim());
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