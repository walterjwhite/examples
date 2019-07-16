package com.walterjwhite.examples.cli;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SerializationExampleOperatingMode implements OperatingMode {
  @DefaultValue
  Default(SerializationExampleCommandLineHandler.class);

  protected final Class<? extends AbstractCommandLineHandler> initiatorClass;
}
