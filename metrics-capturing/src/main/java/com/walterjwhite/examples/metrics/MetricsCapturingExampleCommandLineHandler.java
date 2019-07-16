package com.walterjwhite.examples.metrics;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class MetricsCapturingExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final ExampleService exampleService;

  public static final int MAX_ITERATIONS = Integer.MAX_VALUE;

  @Inject
  public MetricsCapturingExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      ExampleService exampleService) {
    super(shutdownTimeoutInSeconds);
    this.exampleService = exampleService;
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    int i = 0;
    while (i < MAX_ITERATIONS) {
      exampleService.doSomething();
      i++;
    }
  }
}
