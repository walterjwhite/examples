package com.walterjwhite.datastore.examples.querydsl.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class Pizza implements Serializable {
  @Id protected String name;

  @EqualsAndHashCode.Exclude
  @Enumerated(EnumType.STRING)
  @ElementCollection
  protected Set<Topping> toppings = new HashSet<>();

  @EqualsAndHashCode.Exclude
  public Pizza(String name, Set<Topping> toppings, CustomerOrder customerOrder) {
    //    super(name);
    super();
    this.name = name;
    this.toppings = toppings;
    this.customerOrder = customerOrder;
  }

  public Pizza() {
    super();
  }
}
