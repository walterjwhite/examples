package com.walterjwhite.examples.heartbeat;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javax.inject.Inject;

public class HeartbeatExampleCommandLineHandler extends AbstractCommandLineHandler
    implements Heartbeatable {
  protected int i = 0;

  @Inject
  public HeartbeatExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Heartbeat
  @Override
  protected void doRun(String... arguments) {

    while (true) {
      i++;
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void onHeartbeat() {
    System.out.println("heartbeat - " + LocalDateTime.now() + ":" + i);
  }

  @Override
  public Duration getHeartbeatInterval() {
    return Duration.of(1, ChronoUnit.SECONDS);
  }
}
