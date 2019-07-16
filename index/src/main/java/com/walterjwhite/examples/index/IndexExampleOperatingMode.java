package com.walterjwhite.examples.index;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum IndexExampleOperatingMode implements OperatingMode {
  @DefaultValue
  MainApplication(IndexExampleCommandLineHandler.class);

  protected final Class<? extends AbstractCommandLineHandler> initiatorClass;
}
