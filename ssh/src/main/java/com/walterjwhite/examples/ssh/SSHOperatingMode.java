package com.walterjwhite.examples.ssh;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum SSHOperatingMode implements OperatingMode {
  @DefaultValue
  Default(SSHCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  SSHOperatingMode(Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
