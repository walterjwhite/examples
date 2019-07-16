package com.walterjwhite.examples.startup;

import com.walterjwhite.infrastructure.inject.core.service.StartupAware;

public class StartupAwareExample implements StartupAware {
  @Override
  public void onStartup() {
    System.out.println("startup");
  }
}
