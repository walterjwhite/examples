package com.walterjwhite.examples.interruption;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;

import javax.inject.Inject;
import java.util.concurrent.Future;

public class InterruptableExampleCommandLineHandler extends AbstractCommandLineHandler {

  @Inject
  public InterruptableExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  protected void doRun(String... arguments) {
    final InterruptableServiceExample interruptableServiceExample = new InterruptableServiceExample();
    final Future f1 = interruptableServiceExample.run(new InterruptableProcessTaskExample());
    final Future f2 = interruptableServiceExample.run(new InterruptableTaskExample(Integer.MAX_VALUE));

//    try{
//      Thread.sleep(1000);
//    } catch(InterruptedException e){
//      LOGGER.warn("Unable to sleep", e);
//    }
//
//    LOGGER.info("killing ")
  }
}
