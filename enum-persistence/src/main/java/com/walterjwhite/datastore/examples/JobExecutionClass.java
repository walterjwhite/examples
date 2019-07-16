package com.walterjwhite.datastore.examples;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@Entity
public class JobExecutionClass extends AbstractEntity {
  @Column protected Class jobExecutorClass;

  @Column protected String comments;
}
