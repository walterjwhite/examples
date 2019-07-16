package com.walterjwhite.examples.practice.problems.fibonacci;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// up to 100
public class Fibonacci {
  protected final Sequence start = new Sequence(1, new Sequence(0, null));
  protected final int maxValue = 100;

  public void print() {
    final Sequence last = next(start);

    System.out.println("last:" + last.getValue());
    Sequence next = start;
    while (next != null) {
      System.out.println("next:" + next.getValue());
      next = next.getNext();
    }
  }

  protected Sequence next(Sequence current) {
    current.setNext(new Sequence(current.getPrevious().getValue() + current.getValue(), current));

    System.out.println("next:" + current.getNext().getValue());

    if (current.getNext().getValue() < maxValue) return next(current.getNext());

    return current.getNext();
  }

  @Setter
  @Getter
  @RequiredArgsConstructor
  private static class Sequence {
    private final int value;
    private final Sequence previous;

    private Sequence next;
  }
}
