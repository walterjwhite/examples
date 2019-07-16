package com.walterjwhite.datastore.examples.querydsl;

import com.walterjwhite.datastore.examples.querydsl.service.PizzaCriteriaService;

public class TestCriteria extends AbstractPerformanceTest {
  public TestCriteria() {
    super(PizzaCriteriaService.class, 30);
  }
}
