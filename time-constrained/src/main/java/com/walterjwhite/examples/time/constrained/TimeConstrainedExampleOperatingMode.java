package com.walterjwhite.examples.time.constrained;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TimeConstrainedExampleOperatingMode implements OperatingMode {
  @DefaultValue
  Default(TimeConstrainedExampleCommandLineHandler.class);

  protected final Class<? extends AbstractCommandLineHandler> initiatorClass;
}
