package com.walterjwhite.datastore.examples.querydsl;

import com.walterjwhite.inject.cli.providers.guice.test.GuiceTestModule;
import com.walterjwhite.inject.cli.providers.guice.test.PropertyValuePair;
import org.reflections.Reflections;

public class EnumPersistenceTestModule extends GuiceTestModule {
  public EnumPersistenceTestModule(Class testClass, PropertyValuePair... propertyValuePairs) {
    super(testClass, propertyValuePairs);
  }

  public EnumPersistenceTestModule(
      Class testClass, Reflections reflections, PropertyValuePair... propertyValuePairs) {
    super(testClass, reflections, propertyValuePairs);
  }

  @Override
  protected void configure() {
    install(new GoogleGuicePersistModule(/*propertyManager, reflections*/ ));
  }
}
