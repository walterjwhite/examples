package com.walterjwhite.datastore.examples;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@Entity
public class JobExecution extends AbstractEntity {
  // cannot do eumerated here, hibernate will throw an exception
  // @Enumerated
  @Column protected Enum<? extends Job> jobType;

  @Column protected String comments;
}
