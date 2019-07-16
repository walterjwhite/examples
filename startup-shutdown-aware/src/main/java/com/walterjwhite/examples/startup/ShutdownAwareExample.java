package com.walterjwhite.examples.startup;

import com.walterjwhite.infrastructure.inject.core.service.ShutdownAware;

public class ShutdownAwareExample implements ShutdownAware {
  @Override
  public void onShutdown() throws Exception {
    System.out.println("shutdown");
  }
}
