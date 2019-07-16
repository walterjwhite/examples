package com.walterjwhite.examples.identity;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class IdentityExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final EntityCreator entityCreator;

  @Inject
  public IdentityExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      EntityCreator entityCreator) {
    super(shutdownTimeoutInSeconds);
    this.entityCreator = entityCreator;
  }

  @Override
  protected void doRun(String... arguments) {
    entityCreator.createGuestAccount();
    entityCreator.createClientAccount();
  }
}
