package com.walterjwhite.datastore.examples;

public enum EmailJob implements Job {
  Default;

  @Override
  public boolean isRetryable(Exception exception) {
    return false;
  }
}
