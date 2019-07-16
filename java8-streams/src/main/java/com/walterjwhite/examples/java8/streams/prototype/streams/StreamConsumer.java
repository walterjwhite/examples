package com.walterjwhite.examples.java8.streams.prototype.streams;

import java.util.function.Consumer;

public class StreamConsumer implements Consumer<String> {
  @Override
  public void accept(String s) {
    System.out.println(this);
    System.out.println(s);
  }
}
