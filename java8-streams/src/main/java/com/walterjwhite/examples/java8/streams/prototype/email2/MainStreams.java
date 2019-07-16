package com.walterjwhite.examples.java8.streams.prototype.email2;

import java.util.Arrays;
import java.util.List;

public class MainStreams {
  // this example generates sample folder names
  // then for each folder, it generates messages
  // then merely prints out the message
  // flatMap is used to generate a stream of messages from a given folder
  public static void main(final String[] arguments) throws InterruptedException {
    List<String> foldersForEmailAccount =
        Arrays.asList("INBOX", "DRAFTS", "SENT", "TRASH", "FAMILY");

    foldersForEmailAccount.stream()
        .flatMap(new StreamFlatMapFunction())
        .parallel()
        .forEach(new StreamConsumer());
  }
}
