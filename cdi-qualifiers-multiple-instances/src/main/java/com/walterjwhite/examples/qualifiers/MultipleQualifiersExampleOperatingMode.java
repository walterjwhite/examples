package com.walterjwhite.examples.qualifiers;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MultipleQualifiersExampleOperatingMode implements OperatingMode {
  @DefaultValue
  Default(MultipleQualifiersExampleCommandLineHandler.class);

  protected final Class<? extends AbstractCommandLineHandler> initiatorClass;
}
