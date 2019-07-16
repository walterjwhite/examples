package com.walterjwhite.datastore.examples.querydsl;

import com.walterjwhite.datastore.examples.querydsl.model.Pizza;
import com.walterjwhite.datastore.examples.querydsl.model.Topping;
import com.walterjwhite.datastore.examples.querydsl.service.DefaultOrderService;
import com.walterjwhite.datastore.examples.querydsl.service.PizzaService;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTest {
  protected final Class<? extends PizzaService> pizzaServiceClass;
  protected final PizzaService pizzaService;
  protected final DefaultOrderService orderService;

  protected final int orderId;
  protected final int iterations;

  protected AbstractTest(
      Class<? extends PizzaService> pizzaServiceClass,
      PizzaService pizzaService,
      DefaultOrderService orderService,
      int orderId,
      int iterations) {
    this.pizzaServiceClass = pizzaServiceClass;
    this.pizzaService = pizzaService;
    this.orderService = orderService;
    this.orderId = orderId;
    this.iterations = iterations;
  }

  protected void placeOrder() {
    final Set<Topping> toppings = new HashSet<>();
    toppings.add(Topping.Green_Peppers);
    orderService.order(new Pizza("test-JPA", toppings, null), orderId);
  }

  public void execute() {
    placeOrder();

    final long start = System.nanoTime();

    final int COUNT = iterations;
    for (int i = 0; i < iterations; i++) {
      doExecute();
    }

    final double runtime = (System.nanoTime() - start) / 1000000.0;
    System.out.println(pizzaServiceClass + ":runtime:" + runtime + " ms");
    System.out.println(pizzaServiceClass + ":runtime/tx:" + (runtime / (COUNT * 1.0)) + " ms/tx");
  }

  protected abstract void doExecute();
}
