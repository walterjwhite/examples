package com.walterjwhite.examples.java8.streams.prototype;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;

public class Main {
  public static void main(final String[] arguments) throws InterruptedException {
    final String[] stringIterable = new String[] {"Fred", "John"};
    final Disposable o1 = Observable.fromArray(stringIterable).subscribe(new TestConsumer("a"));
    final Disposable o2 = Observable.fromArray(stringIterable).subscribe(new TestConsumer("b"));
    final Disposable o3 = Observable.fromArray(stringIterable).subscribe(new TestConsumer("c"));

    PublishProcessor<String> source = PublishProcessor.create();

    //        source.doOnNext()
    //                .observeOn(Schedulers.computation())
    //                .subscribe(v -> compute(v), Throwable::printStackTrace);
    //
    //        for (int i = 0; i < 1_000_000; i++) {
    //            source.onNext(i);
    //        }
    //
    //        Thread.sleep(10_000);

    System.out.println("n:" + 1_000_000);
    System.out.println("n:" + 1__000_000);
  }
}
