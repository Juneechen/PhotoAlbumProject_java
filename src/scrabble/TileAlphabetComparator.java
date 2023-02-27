package scrabble;

import java.util.Comparator;

public class TileAlphabetComparator implements Comparator<Tile> {
  @Override
  public int compare(Tile o1, Tile o2) {
    return o1.getLetter().compareTo(o2.getLetter());
  }
}
