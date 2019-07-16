package com.walterjwhite.examples.java8.streams.prototype;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class TestPublisher implements Publisher<String> {
  @Override
  public void subscribe(Subscriber<? super String> s) {}
}
