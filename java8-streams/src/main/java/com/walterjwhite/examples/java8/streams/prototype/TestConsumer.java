package com.walterjwhite.examples.java8.streams.prototype;

import io.reactivex.functions.Consumer;

public class TestConsumer implements Consumer<String> {
  protected final String name;

  public TestConsumer(String name) {
    this.name = name;
  }

  @Override
  public void accept(String s) throws Exception {
    //        System.out.println(getClass() + s);
    System.out.println(name + " " + s);
  }
}
