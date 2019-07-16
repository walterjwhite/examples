package com.walterjwhite.examples.index;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;

// this doesn't matter
// the module is going to be picked up automatically
// so, the worker would have to be packaged separately
public class IndexExampleWorkerCommandLineHandler extends AbstractCommandLineHandler {

  public IndexExampleWorkerCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  protected void doRun(String... arguments) {}
}
