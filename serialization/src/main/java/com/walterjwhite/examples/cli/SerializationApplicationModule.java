package com.walterjwhite.examples.cli;

import com.google.inject.Binder;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;
import com.walterjwhite.serialization.modules.snakeyaml.providers.guice.SnakeyamlSerializationServiceModule;

public class SerializationApplicationModule implements GuiceApplicationModule {
  @Override
  public void configure(Binder binder) {
    binder.install(new SnakeyamlSerializationServiceModule());
  }
}
