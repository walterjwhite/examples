package com.walterjwhite.examples.java8.streams.prototype.streams;

import java.util.Arrays;
import java.util.List;

public class MainStreams {
  public static void main(final String[] arguments) throws InterruptedException {
    List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

    myList.stream()
        .filter(new StreamFilterPredicate())
        .map(new StreamMapFunction())
        .sorted()
        .forEach(new StreamConsumer());
  }
}
