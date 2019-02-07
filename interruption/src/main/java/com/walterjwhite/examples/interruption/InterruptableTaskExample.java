package com.walterjwhite.examples.interruption;

import com.walterjwhite.interruptable.Interruptable;
import com.walterjwhite.interruptable.annotation.InterruptableTask;
import java.util.concurrent.Callable;

public class InterruptableTaskExample implements Interruptable, Callable<Void> {
  protected final int maxCount;
  protected boolean interrupted = false;

  public InterruptableTaskExample(int maxCount) {
    this.maxCount = maxCount;
  }

  @InterruptableTask
  public Void call() {
    int i = 0;
    while (!interrupted && i < maxCount) {
      System.out.println("i:" + i++);
    }

    System.out.println("call ended due to interruption:");
    return null;
  }

  @Override
  public void interrupt() {
    interrupted = true;

    System.out.println("interrupted task execution thread");
  }
}
