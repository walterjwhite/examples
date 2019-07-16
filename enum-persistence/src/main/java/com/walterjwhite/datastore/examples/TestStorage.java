package com.walterjwhite.datastore.examples;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.infrastructure.inject.core.annotation.Primary;
import com.walterjwhite.infrastructure.inject.core.annotation.Secondary;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

public class TestStorage {
  protected final Repository primaryRepository;
  //  protected final EntityManager entityManager;
  protected final Repository secondaryRepository;

  @Inject
  public TestStorage(
      @Primary Repository primaryRepository, @Secondary Repository secondaryRepository
      /*EntityManager entityManager*/ ) {
    this.primaryRepository = primaryRepository;
    this.secondaryRepository = secondaryRepository;
    //    this.entityManager = entityManager;
  }

  public void doTestPersistence()
      throws HeuristicRollbackException, RollbackException, HeuristicMixedException,
          SystemException {
    // primaryRepository.getTransaction().begin();
    JobExecution e1 = new JobExecution(IndexJob.Default, "testing - index queuedJob - primary");
    JobExecution e2 = new JobExecution(EmailJob.Default, "testing - email queuedJob - primary ");

    primaryRepository.create(e1);
    primaryRepository.create(e2);
    primaryRepository.getTransaction().commit();

    final JobExecution jobExecution = primaryRepository.findById(JobExecution.class, e1.getId());
    System.out.println(jobExecution.getJobType().getDeclaringClass());

    final JobExecution jobExecution2 = primaryRepository.findById(JobExecution.class, e2.getId());
    System.out.println(jobExecution2.getJobType().getDeclaringClass());
  }

  public void doTestPersistenceClass()
      throws HeuristicRollbackException, RollbackException, HeuristicMixedException,
          SystemException {
    // secondaryRepository.getTransaction().begin();
    JobExecutionClass e1 =
        new JobExecutionClass(IndexJob.class, "testing - index queuedJob - secondary");
    JobExecutionClass e2 =
        new JobExecutionClass(EmailJob.class, "testing - email queuedJob - secondary");

    secondaryRepository.create(e1);
    secondaryRepository.create(e2);
    secondaryRepository.getTransaction().commit();

    final JobExecutionClass jobExecution =
        secondaryRepository.findById(JobExecutionClass.class, e1.getId());
    System.out.println(jobExecution.getJobExecutorClass());

    final JobExecutionClass jobExecution2 =
        secondaryRepository.findById(JobExecutionClass.class, e2.getId());
    System.out.println(jobExecution2.getJobExecutorClass());
  }

  //  public void doTestPersistenceClassPrimary() {
  //    entityManager.getTransaction().begin();
  //    JobExecutionClass e1 =
  //        new JobExecutionClass(IndexJob.class, "testing - index queuedJob - should be primary");
  //    JobExecutionClass e2 =
  //        new JobExecutionClass(EmailJob.class, "testing - email queuedJob - should be primary");
  //
  //    entityManager.create(e1);
  //    entityManager.create(e2);
  //    entityManager.getTransaction().commit();
  //
  //    final JobExecutionClass jobExecution = entityManager.find(JobExecutionClass.class,
  // e1.getId());
  //    System.out.println(jobExecution.getJobExecutorClass());
  //
  //    final JobExecutionClass jobExecution2 = entityManager.find(JobExecutionClass.class,
  // e2.getId());
  //    System.out.println(jobExecution2.getJobExecutorClass());
  //  }
}
