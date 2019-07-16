package com.walterjwhite.datastore.examples.querydsl;

import com.mysema.commons.lang.Assert;
import com.walterjwhite.datastore.examples.querydsl.model.CustomerOrder;
import com.walterjwhite.datastore.examples.querydsl.service.DefaultOrderService;
import com.walterjwhite.datastore.examples.querydsl.service.PizzaService;

public class SearchByOrderIdTest extends AbstractTest {
  public SearchByOrderIdTest(
      Class<? extends PizzaService> pizzaServiceClass,
      PizzaService pizzaService,
      DefaultOrderService orderService,
      int orderId,
      int iterations) {
    super(pizzaServiceClass, pizzaService, orderService, orderId, iterations);
  }

  @Override
  protected void doExecute() {
    CustomerOrder customerOrder = pizzaService.get(orderId);
    Assert.notNull(customerOrder, "Customer Order is null");
    Assert.isTrue(customerOrder.getId() == orderId, "Customer Order id = " + orderId);
  }
}
