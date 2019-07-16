package com.walterjwhite.examples.datanucleus;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import javax.validation.constraints.NotNull;

// @PersistenceAware
@AllArgsConstructor
@NoArgsConstructor
@Data
@PersistenceCapable
public class AuditEntity {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
  protected Integer id;

  protected Class entityClass;

  protected Integer entityId;

  protected String action;
}
