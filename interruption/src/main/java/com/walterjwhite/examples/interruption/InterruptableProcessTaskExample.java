package com.walterjwhite.examples.interruption;

import com.walterjwhite.interruptable.Interruptable;
import com.walterjwhite.interruptable.annotation.InterruptableTask;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Pressing CTRL+C results in a waitFor returning an exit code of 130. Additionally, the process is
 * killed first, the interrupt isn't invoked. Killing the java process results in waitFor returning
 * an exit code of 143, only after the interrupt method kills the process.
 */
public class InterruptableProcessTaskExample implements Interruptable, Callable<Void> {
  protected Process process;

  @InterruptableTask
  public Void call() {
    try {
      process = Runtime.getRuntime().exec("sleep 600");
      System.out.println("waitFor status:" + process.waitFor());
      return null;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Error during execution", e);
    }
  }

  @Override
  public void interrupt() {
    if (process != null && process.isAlive()) {
      System.out.println("interrupted task via interrupt");
      process.destroy();
    }

    System.out.println("exit interrupt process");
  }
}
