package com.walterjwhite.examples.multiple;

import javax.inject.Inject;
import javax.inject.Provider;

public class PrimaryParentProvider implements Provider<ParentService> {
  protected final ChildService childService;
  protected final ParentService parentService;

  @Inject
  public PrimaryParentProvider(ChildService childService) {
    this.childService = childService;
    parentService = new ParentService("primary", childService);
  }

  @Override
  public ParentService get() {
    return parentService;
  }
}
