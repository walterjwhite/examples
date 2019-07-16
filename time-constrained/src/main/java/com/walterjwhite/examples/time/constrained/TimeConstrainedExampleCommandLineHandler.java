package com.walterjwhite.examples.time.constrained;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import javax.inject.Inject;

public class TimeConstrainedExampleCommandLineHandler extends AbstractCommandLineHandler
    implements TimeConstrainedMethodInvocation {
  protected int i = 0;

  @Inject
  public TimeConstrainedExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @TimeConstrained
  @Override
  protected void doRun(String... arguments) {
    while (true) {
      try {
        System.out.println("i:" + i++);
        Thread.sleep(50);

      } catch (InterruptedException e) {
        System.out.println("Exiting as instructed to do so.");
        System.exit(1);
      }
    }
  }

  @Override
  public Duration getAllowedExecutionDuration() {
    return Duration.of(1, ChronoUnit.SECONDS);
  }
}
