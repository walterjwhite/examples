package com.walterjwhite.examples.java8.streams.prototype;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class TestSubscriber implements Subscriber<String> {
  @Override
  public void onSubscribe(Subscription s) {
    s.request(1);
  }

  @Override
  public void onNext(String s) {
    System.out.println("next:" + s);
  }

  @Override
  public void onError(Throwable t) {
    t.printStackTrace();
  }

  @Override
  public void onComplete() {
    System.out.println("done");
  }
}
