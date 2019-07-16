package com.walterjwhite.examples.cdi;

import java.util.Properties;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class Main {
  public static void main(final String[] arguments) {
    SeContainerInitializer initializer = SeContainerInitializer.newInstance();

    // enable auto-discovery (requires beans.xml to be present)
    SeContainer seContainer = initializer.disableDiscovery().addPackages(Main.class).initialize();

    final Properties applicationProperties = new Properties();

    //    seContainer.getBeanManager().createBeanAttributes(Property.class);
    //    seContainer.getBeanManager().createBean();
  }
}
