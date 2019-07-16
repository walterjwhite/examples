package com.walterjwhite.datastore.examples.querydsl;

import com.walterjwhite.datastore.examples.querydsl.service.PizzaJPAService;

public class TestJPA extends AbstractPerformanceTest {
  public TestJPA() {
    super(PizzaJPAService.class, 10);
  }
}
