package model;

import java.util.Objects;

/**
 * Point class representing a 2D point.
 */
public class Point {
  private final int x, y;

  /**
   * construct a point.
   * @param x represents the x coordinate.
   * @param y represents the y coordinate.
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * return the y coordinate.
   * @return the y coordinate.
   */
  public int getY() {
    return this.y;
  }

  /**
   * return the x coordinate.
   * @return the x coordinate.
   */
  public int getX() {
    return this.x;
  }


  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    Point o = (Point) obj;
    return Double.compare(o.getX(), this.x) == 0 && Double.compare(o.getY(), this.y) == 0;
  }

  @Override
  public String toString() {
    return String.format("(%d, %d)", this.x, this.y);
  }
}
