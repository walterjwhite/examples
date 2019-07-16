package com.walterjwhite.examples.qualifiers;

import com.walterjwhite.examples.qualifiers.service.ExampleService;
import com.walterjwhite.infrastructure.inject.core.annotation.Primary;
import com.walterjwhite.infrastructure.inject.core.annotation.Secondary;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class MultipleQualifiersExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final ExampleService primaryExampleService;
  protected final ExampleService secondaryExampleService;
  protected final ExampleService unqualifiedExampleService;

  @Inject
  public MultipleQualifiersExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Primary ExampleService unqualifiedExampleService,
      ExampleService primaryExampleService,
      @Secondary ExampleService secondaryExampleService) {
    super(shutdownTimeoutInSeconds);

    this.unqualifiedExampleService = unqualifiedExampleService;
    this.primaryExampleService = primaryExampleService;
    this.secondaryExampleService = secondaryExampleService;
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    primaryExampleService.print();
    unqualifiedExampleService.print();
    secondaryExampleService.print();
  }
}
