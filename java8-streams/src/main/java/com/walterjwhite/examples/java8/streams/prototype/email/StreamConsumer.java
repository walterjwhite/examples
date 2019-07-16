package com.walterjwhite.examples.java8.streams.prototype.email;

import java.util.function.Consumer;

public class StreamConsumer implements Consumer<String[]> {
  @Override
  public void accept(String[] ss) {
    System.out.println(this);

    for (final String s : ss) System.out.println(s);
  }
}
