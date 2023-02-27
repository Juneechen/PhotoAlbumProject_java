package scrabble;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScrabbleBag {
  private List<Tile> allTiles;
  private HashMap<String, Integer> valueMap;

  public ScrabbleBag() {
    this.allTiles = new LinkedList<>();
    this.valueMap = new HashMap<>();
  }

  private boolean isLetter(String str) {
    return str != null && str.length() == 1 && Character.isLetter(str.charAt(0));
  }

  private boolean hasDiffValue(String str, int value) {
    return (valueMap.containsKey(str) && valueMap.get(str) != value);
  }

  public void addTile(String aLetter, int value) throws IllegalArgumentException {
    if (!isLetter(aLetter)) {
      throw new IllegalArgumentException("invalid letter");
    }
    if (hasDiffValue(aLetter, value)) {
      throw new IllegalArgumentException("different value for this letter is seen before");
    }

    allTiles.add(new Tile(aLetter, value));
    if (! valueMap.containsKey(aLetter)) {
      valueMap.put(aLetter, value);
    }
  }

  /**
   * adds a independent copy of the referenced Tile.
   * @param aTile to add.
   * @throws IllegalArgumentException if invalid.
   */
  public void addTile(Tile aTile) throws IllegalArgumentException {
    if (aTile == null) {
      throw new IllegalArgumentException("Tile cannot be null");
    }
    addTile(aTile.getLetter(), aTile.getValue()); // handles letter and value check and add
  }

  /**
   * assumes the bag can contain multiple Tiles of the same letter with the same value;
   * removes and returns one instance of Tile with the specified letter if one exists.
   * @param aLetter a letter to look for.
   * @return one Tile with aLetter if exists, null otherwise.
   */
  public Tile removeTile(String aLetter) {
    if (!valueMap.containsKey(aLetter)) {
      return null;
    }
    List<Tile> target = allTiles.stream().filter(t -> t.getLetter().equals(aLetter)).
            collect(Collectors.toList());
    if (target.isEmpty()) {
      return null;
    }
    Tile toRemove = target.get(0);
    allTiles.remove(toRemove);
    return toRemove;
  }

  public List<Tile> getAllTiles() {
    return allTiles;
  }

  public List<Tile> filter(Predicate<Tile> pred) {
    allTiles.stream().filter(pred).collect(Collectors.toList());
  }
}
