package com.walterjwhite.examples.interruption;

import com.walterjwhite.interruptable.Interruptable;
import com.walterjwhite.interruptable.annotation.InterruptableApplication;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class InterruptableApplicationExample implements Interruptable {
  @InterruptableApplication
  public void run() throws ExecutionException, InterruptedException {
    final InterruptableServiceExample interruptableServiceExample =
        new InterruptableServiceExample();

    Set<Future> futures = new HashSet<>();
    futures.add(interruptableServiceExample.run(new InterruptableTaskExample(1000000)));
    futures.add(interruptableServiceExample.run(new InterruptableTaskExample(10)));
    // final Future f = interruptableServiceExample.run(new InterruptableProcessTaskExample());

    futures
        .stream()
        .forEach(
            f -> {
              try {
                f.get();
              } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException("error getting", e);
              }
            });
  }

  public static void main(final String[] arguments)
      throws ExecutionException, InterruptedException {
    new InterruptableApplicationExample().run();
  }

  @Override
  public void interrupt() {
    System.out.println("interrupted application-level:" + getClass());
  }
}
