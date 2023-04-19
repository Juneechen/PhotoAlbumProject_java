package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * snapshot of a collection of IShape.
 */
public class Snapshot implements ISnapshot {
  private static final String ID_Format = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";
  private static final String TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
  private final LocalDateTime time;
  private final String id;
  private final String timestamp;
  private final String description;
  private final String shapesInfo;
  private final List<IShape> shapes;

  /**
   * create a snapshot on a collection of objects;
   * a timestamp and id will be generated
   * based on the current local time and the format specified in the Snapshot class.
   *
   * @param note description of the snapshot.
   * @param shapes a collection of shapes to be captured.
   */
  public Snapshot(String note, Collection<IShape> shapes) {
    this.time = LocalDateTime.now();
    this.id = this.time.format(DateTimeFormatter.ofPattern(ID_Format));
    this.timestamp = this.time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    this.description = note;
    this.shapesInfo = shapes.stream().map(Object::toString).collect(Collectors.joining("\n"));
    this.shapes = new ArrayList<>(shapes); // shapes is already a deep copy of the original shapes
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public List<IShape> getShapes() {
    return Collections.unmodifiableList(shapes);
  }

  @Override
  public String toString() {
    return String.join("\n", "Snapshot ID: " + this.id,
            "Timestamp: " + this.timestamp,
            "Description: " + this.description,
            "Shape Information:", this.shapesInfo);
  }
}
