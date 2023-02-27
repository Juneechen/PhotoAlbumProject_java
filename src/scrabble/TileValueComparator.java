package scrabble;

import java.util.Comparator;

public class TileValueComparator implements Comparator<Tile> {
  @Override
  public int compare(Tile o1, Tile o2) {
    return Integer.compare(o1.getValue(), o2.getValue());
  }
}
