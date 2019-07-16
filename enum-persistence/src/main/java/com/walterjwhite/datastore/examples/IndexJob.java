package com.walterjwhite.datastore.examples;

public enum IndexJob implements Job {
  Default;

  @Override
  public boolean isRetryable(Exception exception) {
    return true;
  }
}
