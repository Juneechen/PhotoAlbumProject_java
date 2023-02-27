package pairs;

import java.util.Objects;

public class Pair<X, Y> {
  private X first;
  private Y second;

  public Pair(X first, Y second) {
    this.first = first;
    this.second = second;
  }

  public X getFirst() {
    return this.first;
  }

  public Y getSecond() {
    return this.second;
  }

  @Override
  public String toString() {
    return "<" + first + ", " + second + ">\n";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Pair<X, Y> o = (Pair<X, Y>) obj;
    return this.first.equals(o.first)
            && this.second.equals(o.second); // assumes X, Y are non-primitive
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.first, this.second);
  }
}
