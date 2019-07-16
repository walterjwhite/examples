package com.walterjwhite.examples.qualifiers;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

public class WalterJWhiteExtension implements Extension {
  <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> processAnnotatedType) {
    if (!processAnnotatedType
        .getAnnotatedType()
        .getJavaClass()
        .getName()
        .startsWith("com.walterjwhite")) {
      processAnnotatedType.veto();
    }
  }
}
