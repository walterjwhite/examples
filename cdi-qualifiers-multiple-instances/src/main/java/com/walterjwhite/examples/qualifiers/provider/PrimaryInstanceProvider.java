package com.walterjwhite.examples.qualifiers.provider;

import com.walterjwhite.examples.qualifiers.service.ExampleService;
import com.walterjwhite.infrastructure.inject.core.annotation.Primary;
import javax.enterprise.inject.Produces;

public class PrimaryInstanceProvider {

  @Primary
  @Produces
  public ExampleService produceExampleService() {
    return new ExampleService("primary");
  }
}
