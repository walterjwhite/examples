package com.walterjwhite.datastore.examples.querydsl.service;

import com.walterjwhite.datastore.examples.querydsl.model.*;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;

public class PizzaJooqService implements PizzaService {
  protected final Provider<EntityManager> entityManagerProvider;

  @Inject
  public PizzaJooqService(Provider<EntityManager> entityManagerProvider) {
    super();
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public CustomerOrder save(CustomerOrder customerOrder) {
    entityManagerProvider.get().persist(customerOrder);

    return customerOrder;
  }

  public CustomerOrder get(int orderId) {
    return null;
  }

  public List<Pizza> get(Topping topping) {
    return null;
  }

  public List<Pizza> get(final String name) {
    return null;
  }
}
