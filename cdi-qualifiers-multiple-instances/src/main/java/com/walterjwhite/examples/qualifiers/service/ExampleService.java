package com.walterjwhite.examples.qualifiers.service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExampleService {
  protected final String instanceName;

  public String print() {
    return "hi from " + instanceName;
  }
}
