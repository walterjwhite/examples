package com.walterjwhite.examples.multiple;

import com.walterjwhite.infrastructure.inject.core.annotation.Primary;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

// TODO: remove this, this was put here to see if this would hypothetically work as then it'd be
// compatible with Guice and CDI
// I think CDI will also require some special "glue"
@Deprecated
public class ParentProvider {
  @Inject
  @Primary
  @Produces
  public ParentService producePrimary(ChildService childService) {
    return new ParentService("primary", childService);
  }

  @Inject
  @Secondary
  @Produces
  public ParentService produceSecondary(ChildService childService) {
    return new ParentService("secondary", childService);
  }
}
