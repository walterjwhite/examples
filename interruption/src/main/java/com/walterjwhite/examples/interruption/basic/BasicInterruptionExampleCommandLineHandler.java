package com.walterjwhite.examples.interruption.basic;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;

import javax.inject.Inject;
import java.io.IOException;

// this doesn't show anything, run() blocks
public class BasicInterruptionExampleCommandLineHandler extends AbstractCommandLineHandler {
  @Inject
  public BasicInterruptionExampleCommandLineHandler( @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  public static void main(final String[] arguments) throws IOException, InterruptedException {
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    System.out.println("Shutdown time:" + Thread.currentThread());
    final ExampleWorker w1 = new ExampleWorker();
    w1.run();

    w1.kill();
  }
}
