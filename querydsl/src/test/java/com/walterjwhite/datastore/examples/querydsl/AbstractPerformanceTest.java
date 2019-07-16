package com.walterjwhite.datastore.examples.querydsl;

import com.google.inject.Injector;
import com.walterjwhite.datastore.examples.querydsl.service.DefaultOrderService;
import com.walterjwhite.datastore.examples.querydsl.service.PizzaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractPerformanceTest {
  protected final Class<? extends PizzaService> pizzaServiceClass;

  protected final int orderId;
  protected final int iterations;

  protected Injector injector;

  protected PizzaService pizzaService;
  protected DefaultOrderService orderService;

  public AbstractPerformanceTest(Class<? extends PizzaService> pizzaServiceClass, int orderId) {
    this(pizzaServiceClass, orderId, 100000);
  }

  protected AbstractPerformanceTest(
      Class<? extends PizzaService> pizzaServiceClass, int orderId, int iterations) {
    this.pizzaServiceClass = pizzaServiceClass;
    this.orderId = orderId;
    this.iterations = iterations;
  }

  @Before
  public void onBefore() {
    //    GuiceHelper.addModules(new QuerydslModule(getClass(), pizzaServiceClass));
    //    GuiceHelper.setup();
    //
    //    injector = GuiceHelper.getGuiceApplicationInjector();
    pizzaService = injector.getInstance(PizzaService.class);
    orderService = injector.getInstance(DefaultOrderService.class);
  }

  @After
  public void onAfter() {
    //    GuiceHelper.stop();
  }

  @Test
  public void doSearchByOrderIdTest() {
    new SearchByOrderIdTest(pizzaServiceClass, pizzaService, orderService, orderId, iterations)
        .execute();
  }

  @Test
  public void doSearchByNameTest() {
    new SearchByNameTest(pizzaServiceClass, pizzaService, orderService, orderId, iterations)
        .execute();
  }

  //  @Test
  //  public void doPerformanceTestGetByToppings() {
  //    new SearchByToppingsTest(pizzaServiceClass, pizzaService, orderService, orderId,
  // iterations).execute();
  //
  //  }
}
