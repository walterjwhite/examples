package com.walterjwhite.examples.email;

import com.walterjwhite.examples.email.model.EmailAccountRules;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailAccountRulesRunnable implements Runnable {
  protected final EmailAccountRules emailAccountRules;

  @Override
  public void run() {
    emailAccountRules.process();
  }
}
