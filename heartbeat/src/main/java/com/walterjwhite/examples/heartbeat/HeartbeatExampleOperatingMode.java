package com.walterjwhite.examples.heartbeat;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HeartbeatExampleOperatingMode implements OperatingMode {
  @DefaultValue
  Default(HeartbeatExampleCommandLineHandler.class);

  protected final Class<? extends AbstractCommandLineHandler> initiatorClass;
}
