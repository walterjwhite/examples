package com.walterjwhite.examples.java8.streams.prototype.email;

import java.util.Arrays;
import java.util.List;

public class MainStreams {
  public static void main(final String[] arguments) throws InterruptedException {
    List<String> foldersForEmailAccount = Arrays.asList("a1", "a2", "b1", "c2", "c1");

    foldersForEmailAccount.stream()
        .map(new StreamMapFunction())
        .parallel()
        .forEach(new StreamConsumer());
  }
}
