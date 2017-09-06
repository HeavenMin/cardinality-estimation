// Pair.java
// Basic implementation of a pair class
// awirth for COMP90056 (based on downloaded material)
// Aug 2017

public class Pair<L,R> {

  private final L left;
  private final R right;

  public Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  public L getLeft() { return left; }
  public R getRight() { return right; }

  @Override
  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pair)) return false;
    Pair<L,R> pairo = (Pair<L,R>) o;
    return this.left.equals(pairo.getLeft()) &&
           this.right.equals(pairo.getRight());
  }

}