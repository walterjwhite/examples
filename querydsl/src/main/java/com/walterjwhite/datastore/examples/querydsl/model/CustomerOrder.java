package com.walterjwhite.datastore.examples.querydsl.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class CustomerOrder implements Serializable {
  @Id protected int id;

  @EqualsAndHashCode.Exclude @EqualsAndHashCode.Exclude
  protected List<Pizza> pizzas = new ArrayList<>();

  public CustomerOrder() {
    super();
  }
}
