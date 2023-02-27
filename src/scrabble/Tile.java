package scrabble;

import java.util.Objects;

import pairs.Pair;

public class Tile {
  private final Pair<String, Integer> letterValue;

  public Tile(String letter, int value) {
    // TA suggests not to throw exceptions not explicitly required by instruction,
    // so the following are removed:
    //    if (letter == null || letter.length() != 1) {
    //      throw new IllegalArgumentException("not a letter");
    //    }
    this.letterValue = new Pair<>(letter, value);
  }

  public String getLetter() {
    return this.letterValue.getFirst();
  }

  public int getValue() {
    return this.letterValue.getSecond();
  }

  public boolean isVowel() { // returns true if letter is a vowel (AEIOU)
    return "AEIOU".contains(this.getLetter()); // assumes letter.length() == 1
  }

  @Override
  public String toString() {
    return this.getLetter() + ":" + this.getValue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.letterValue);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (this.getClass() != obj.getClass())
      return false;
    Tile o = (Tile) obj;
    return this.getLetter().equals(o.getLetter())
            && this.getValue() == (o.getValue());
  }

  public Tile copy(Tile o) {
    if (o == null) {
      return null;
    }
    return new Tile(o.getLetter(), o.getValue());
  }
}
