package com.walterjwhite.datastore.examples.querydsl;

import com.google.inject.Injector;
import com.walterjwhite.datastore.examples.EmailJob;
import com.walterjwhite.datastore.examples.IndexJob;
import com.walterjwhite.datastore.examples.JobExecution;
import com.walterjwhite.datastore.examples.JobExecutionClass;
import org.junit.Before;
import org.junit.Test;

public class TestStorage {
  protected Injector injector;

  @Before
  public void onSetup() {
    GuiceInjectorService.addModules(new EnumPersistenceTestModule(getClass()));
    GuiceInjectorService.setup();

    injector = GuiceInjectorService.getInjector();
  }

  @Test
  public void doTestPersistence() {
    EntityManager entityManager = injector.getInstance(EntityManager.class);

    entityManager.getTransaction().begin();
    JobExecution e1 = new JobExecution(IndexJob.Default, "testing - index queuedJob");
    JobExecution e2 = new JobExecution(EmailJob.Default, "testing - email queuedJob");

    entityManager.persist(e1);
    entityManager.persist(e2);
    entityManager.getTransaction().commit();

    final JobExecution jobExecution = entityManager.find(JobExecution.class, e1.getId());
    System.out.println(jobExecution.getJobType().getDeclaringClass());

    final JobExecution jobExecution2 = entityManager.find(JobExecution.class, e2.getId());
    System.out.println(jobExecution2.getJobType().getDeclaringClass());
  }

  @Test
  public void doTestPersistenceClass() {
    EntityManager entityManager = injector.getInstance(EntityManager.class);

    entityManager.getTransaction().begin();
    JobExecutionClass e1 = new JobExecutionClass(IndexJob.class, "testing - index queuedJob");
    JobExecutionClass e2 = new JobExecutionClass(EmailJob.class, "testing - email queuedJob");

    entityManager.persist(e1);
    entityManager.persist(e2);
    entityManager.getTransaction().commit();

    final JobExecutionClass jobExecution = entityManager.find(JobExecutionClass.class, e1.getId());
    System.out.println(jobExecution.getJobExecutorClass());

    final JobExecutionClass jobExecution2 = entityManager.find(JobExecutionClass.class, e2.getId());
    System.out.println(jobExecution2.getJobExecutorClass());
  }
}
