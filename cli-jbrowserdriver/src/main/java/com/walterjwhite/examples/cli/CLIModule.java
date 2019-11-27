package com.walterjwhite.examples.cli;

import com.google.inject.AbstractModule;
import com.walterjwhite.browser.jbrowserdriver.JBrowserDriverModule;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;

public class CLIModule extends AbstractModule implements GuiceApplicationModule {

  @Override
  protected void configure() {
    install(new JBrowserDriverModule());
  }
}
