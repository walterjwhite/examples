package com.walterjwhite.examples.queue;

import com.walterjwhite.property.impl.annotation.Property;
import com.walterjwhite.queue.api.annotation.Job;
import com.walterjwhite.queue.api.annotation.JobExecutionConfiguration;
import com.walterjwhite.queue.api.annotation.scheduling.FixedDelay;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.queue.impl.worker.property.JobExecutionHeartbeatTimeoutUnits;
import com.walterjwhite.queue.impl.worker.property.JobExecutionHeartbeatTimeoutValue;
import java.time.temporal.ChronoUnit;
import javax.inject.Inject;

/** Runs every 10 seconds with no initial delay */
@Job(
    jobExecutionConfiguration = @JobExecutionConfiguration(),
    fixedDelay = @FixedDelay(fixedDelay = 10))
public class SimpleQueuedJob extends AbstractRunnable {
  protected int invocationCount = 0;

  @Inject
  public SimpleQueuedJob(
      @Property(JobExecutionHeartbeatTimeoutValue.class) long heartbeatIntervalValue,
      @Property(JobExecutionHeartbeatTimeoutUnits.class) ChronoUnit heartbeatIntervalUnits) {
    super();
  }

  @Override
  protected void doCall() {
    System.out.println("called:" /*+ LocalDateTime.now()*/ + ":" + invocationCount++);
  }
}
