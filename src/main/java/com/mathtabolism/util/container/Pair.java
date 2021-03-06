/**
 * 
 */
package com.mathtabolism.util.container;

/**
 * 
 * @author mlaursen
 */
public class Pair<T> {
  public final T first;
  public final T second;
  
  public Pair() {
    throw new InstantiationError("Pair can not be instantiated with no arguments.");
  }
  
  public Pair(T first, T second) {
    this.first = first;
    this.second = second;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object != null && object instanceof Pair) {
      Pair<?> t = (Pair<?>) object;
      return first.getClass() == t.first.getClass() && first.equals(t.first) && second.equals(t.second);
    }
    return false;
  }
  
  @Override
  public String toString() {
    return String.format("Pair<%s, %s>", first.toString(), second.toString());
  }
}
