package com.walterjwhite.examples.qualifiers.provider;

import com.walterjwhite.examples.qualifiers.service.ExampleService;
import com.walterjwhite.infrastructure.inject.core.annotation.Secondary;
import javax.enterprise.inject.Produces;

public class SecondaryInstanceProvider {
  @Secondary
  @Produces
  public ExampleService produceExampleService() {
    return new ExampleService("secondary");
  }
}
