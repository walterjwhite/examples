package com.walterjwhite.datastore.examples;

import com.google.inject.Binder;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;

public class EnumModule implements GuiceApplicationModule {
  @Override
  public void configure(Binder binder) {
    //    binder.install(new GoogleGuicePersistModule());
  }
}
