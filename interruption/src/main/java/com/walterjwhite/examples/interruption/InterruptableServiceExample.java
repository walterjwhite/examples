package com.walterjwhite.examples.interruption;

import com.walterjwhite.interruptable.Interruptable;
import com.walterjwhite.interruptable.annotation.InterruptableService;
import java.util.concurrent.*;

@InterruptableService
public class InterruptableServiceExample implements Interruptable {
  protected final ExecutorService executorService =
      Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

  public Future run(final Callable callable) {
    return executorService.submit(callable);
  }

  @Override
  public void interrupt() {
    System.out.println("interrupting service - InterruptableServiceExample");
  }
}
