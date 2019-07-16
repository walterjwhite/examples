package com.walterjwhite.examples.java8.streams.prototype.email2;

import java.util.function.Consumer;

public class StreamConsumer implements Consumer<String> {
  @Override
  public void accept(String message) {
    System.out.println(this);
    System.out.println(message);
  }
}
