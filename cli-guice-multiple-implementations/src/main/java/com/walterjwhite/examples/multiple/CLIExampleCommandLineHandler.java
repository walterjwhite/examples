package com.walterjwhite.examples.multiple;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class CLIExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final ParentService primaryParentService;
  protected final ParentService secondaryParentService;

  @Inject
  public CLIExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Primary ParentService primaryParentService,
      @Secondary ParentService secondaryParentService) {
    super(shutdownTimeoutInSeconds);
    this.primaryParentService = primaryParentService;
    this.secondaryParentService = secondaryParentService;
  }

  @Override
  protected void doRun(String... arguments) {
    primaryParentService.print();
    secondaryParentService.print();
  }
}
