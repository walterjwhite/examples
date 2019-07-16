package com.walterjwhite.datastore.examples.querydsl.service;

import com.walterjwhite.datastore.examples.querydsl.model.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Provider;

public class PizzaCriteriaService implements PizzaService {

  protected final Provider<EntityManager> entityManagerProvider;

  @Inject
  public PizzaCriteriaService(Provider<EntityManager> entityManagerProvider) {
    super();
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public CustomerOrder save(CustomerOrder customerOrder) {
    entityManagerProvider.get().persist(customerOrder);

    return customerOrder;
  }

  public CustomerOrder get(int orderId) {
    EntityManager entityManager = entityManagerProvider.get();
    entityManager.clear();

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<CustomerOrder> criteriaQuery = criteriaBuilder.createQuery(CustomerOrder.class);
    Root<CustomerOrder> root = criteriaQuery.from(CustomerOrder.class);

    Predicate orderIdPredicate = criteriaBuilder.equal(root.get(CustomerOrder_.id), orderId);

    criteriaQuery.where(orderIdPredicate);

    return (entityManager
        .createQuery(criteriaQuery)
        .setFirstResult(0)
        .setMaxResults(1)
        .getSingleResult());
  }

  public List<Pizza> get(Topping topping) {
    EntityManager entityManager = entityManagerProvider.get();
    entityManager.clear();

    CriteriaQuery<Pizza> criteriaQuery =
        entityManager.getCriteriaBuilder().createQuery(Pizza.class);
    Root<Pizza> root = criteriaQuery.from(Pizza.class);
    final Set<Topping> toppings = new HashSet<>();
    toppings.add(topping);
    criteriaQuery.where(root.get(Pizza_.toppings).in(toppings));

    return (entityManager.createQuery(criteriaQuery).getResultList());
  }

  public List<Pizza> get(final String name) {
    EntityManager entityManager = entityManagerProvider.get();
    entityManager.clear();

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Pizza> criteriaQuery = criteriaBuilder.createQuery(Pizza.class);
    Root<Pizza> root = criteriaQuery.from(Pizza.class);

    Predicate namePredicate = criteriaBuilder.equal(root.get(Pizza_.name), name);

    criteriaQuery.where(namePredicate);

    return (entityManager.createQuery(criteriaQuery).getResultList());
  }
}
