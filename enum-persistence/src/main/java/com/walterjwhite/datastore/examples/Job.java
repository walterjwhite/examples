package com.walterjwhite.datastore.examples;

public interface Job {
  boolean isRetryable(final Exception exception);
}
