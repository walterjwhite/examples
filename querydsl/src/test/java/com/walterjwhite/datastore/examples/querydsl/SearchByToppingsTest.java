package com.walterjwhite.datastore.examples.querydsl;

import com.mysema.commons.lang.Assert;
import com.walterjwhite.datastore.examples.querydsl.model.Pizza;
import com.walterjwhite.datastore.examples.querydsl.model.Topping;
import com.walterjwhite.datastore.examples.querydsl.service.DefaultOrderService;
import com.walterjwhite.datastore.examples.querydsl.service.PizzaService;
import java.util.List;

public class SearchByToppingsTest extends AbstractTest {
  public SearchByToppingsTest(
      Class<? extends PizzaService> pizzaServiceClass,
      PizzaService pizzaService,
      DefaultOrderService orderService,
      int orderId,
      int iterations) {
    super(pizzaServiceClass, pizzaService, orderService, orderId, iterations);
  }

  @Override
  protected void doExecute() {
    List<Pizza> pizzas = pizzaService.get(Topping.Green_Peppers);
    Assert.notNull(pizzas, "Expected at least some matching pizzas");
    Assert.isTrue(
        pizzas.get(0).getToppings().contains(Topping.Green_Peppers),
        "Expected green peppers as a topping");
  }
}
