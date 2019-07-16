package com.walterjwhite.datastore.examples;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class EnumPersistenceCommandLineHandler extends AbstractCommandLineHandler {
  protected final TestStorage testStorage;

  @Inject
  public EnumPersistenceCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) final int shutdownTimeoutInSeconds,
      TestStorage testStorage) {
    super(shutdownTimeoutInSeconds);
    this.testStorage = testStorage;
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    testStorage.doTestPersistence();
    testStorage.doTestPersistenceClass();
  }
}
