// package com.walterjwhite.datastore.examples.querydsl;
//
// import com.walterjwhite.datastore.examples.querydsl.service.PizzaService;
// import org.reflections.Reflections;
//
// public class QuerydslModule extends GuiceTestModule {
//  protected final Class<? extends PizzaService> pizzaServiceClass;
//
//  public QuerydslModule(
//      Class testClass,
//      Class<? extends PizzaService> pizzaServiceClass,
//      PropertyValuePair... propertyValuePairs) {
//    super(testClass, propertyValuePairs);
//    this.pizzaServiceClass = pizzaServiceClass;
//  }
//
//  public QuerydslModule(
//      Class testClass,
//      Reflections reflections,
//      Class<? extends PizzaService> pizzaServiceClass,
//      PropertyValuePair... propertyValuePairs) {
//    super(testClass, reflections, propertyValuePairs);
//    this.pizzaServiceClass = pizzaServiceClass;
//  }
//
//  @Override
//  protected void configure() {
//    bind(PizzaService.class).to(pizzaServiceClass);
//
//    install(new GoogleGuicePersistModule(/*propertyManager, reflections*/));
//  }
// }
