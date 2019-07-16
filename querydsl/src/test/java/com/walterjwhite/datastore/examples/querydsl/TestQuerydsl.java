package com.walterjwhite.datastore.examples.querydsl;

import com.walterjwhite.datastore.examples.querydsl.service.PizzaQuerydslService;

public class TestQuerydsl extends AbstractPerformanceTest {
  public TestQuerydsl() {
    super(PizzaQuerydslService.class, 20);
  }
}
