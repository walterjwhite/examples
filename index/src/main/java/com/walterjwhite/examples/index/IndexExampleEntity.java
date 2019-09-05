package com.walterjwhite.examples.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@Entity
public class IndexExampleEntity extends AbstractEntity {
  protected String name;

  protected LocalDateTime dateTime;

  protected int favoriteNumber;
}
