package com.walterjwhite.examples.interruption.basic;

import java.io.IOException;

public class InterruptionExample {
  public static void main(final String[] arguments) throws IOException, InterruptedException {
    final ExampleWorker w1 = new ExampleWorker();
    Runtime.getRuntime().addShutdownHook(new ShutdownHook(w1));
    w1.run();
  }

  private static class ShutdownHook extends Thread {
    protected final ExampleWorker[] workers;

    private ShutdownHook(ExampleWorker... workers) {
      this.workers = workers;
    }

    @Override
    public void run() {
      System.out.println("Shutdown time:" + Thread.currentThread());

      for (ExampleWorker exampleWorker : workers) exampleWorker.kill();
    }
  }
}
