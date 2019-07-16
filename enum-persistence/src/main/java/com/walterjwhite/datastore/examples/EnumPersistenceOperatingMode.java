package com.walterjwhite.datastore.examples;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumPersistenceOperatingMode implements OperatingMode {
  @DefaultValue
  Default(EnumPersistenceCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;
}
