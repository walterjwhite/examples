package com.walterjwhite.examples.startup;

import com.google.inject.AbstractModule;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;

public class StartupGuiceCLIModule extends AbstractModule implements GuiceApplicationModule {

  @Override
  protected void configure() {
    bind(StartupAwareExample.class);
    bind(ShutdownAwareExample.class);
  }
}
