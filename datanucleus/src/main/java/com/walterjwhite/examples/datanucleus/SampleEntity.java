package com.walterjwhite.examples.datanucleus;

import javax.jdo.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import javax.validation.constraints.NotNull;

// @PersistenceAware
@AllArgsConstructor
@NoArgsConstructor
@Data
@PersistenceCapable
public class SampleEntity {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
  protected Integer id;

  // TODO: problem with bean validation interfering with enhancement
  // @NotNull(message = "First Name is required, how many people do you know without a first name?")
  //    @NotNull
  protected String firstName;
  //    @NotNull(message = "Last Name is required, how many people do you know without a last
  // name?")
  protected String lastName;
}
