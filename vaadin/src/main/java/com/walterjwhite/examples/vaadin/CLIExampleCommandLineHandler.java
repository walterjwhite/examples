package com.walterjwhite.examples.vaadin;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import java.util.Arrays;
import javax.inject.Inject;

public class CLIExampleCommandLineHandler extends AbstractCommandLineHandler {

  @Inject
  public CLIExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  protected void doRun(String... arguments) {
    System.out.println("doRun:start");
    Arrays.stream(arguments).forEach(argument -> System.out.println(argument));
    System.out.println("doRun:end");
  }
}
