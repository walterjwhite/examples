package com.walterjwhite.examples.datanucleus;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import java.util.List;
import javax.inject.Inject;
import javax.jdo.*;

public class DatanucleusExampleCommandLineHandler extends AbstractCommandLineHandler {
  @Inject
  public DatanucleusExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  protected void doRun(String... arguments) {
    final PersistenceManagerFactory persistenceManagerFactory =
        JDOHelper.getPersistenceManagerFactory("Primary");

    // System.out.println("chrono unit:" + ChronoUnit.SECONDS);

    PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
    persistenceManager.addInstanceLifecycleListener(
        new SampleLifecycleListener(persistenceManager), null);

    Transaction transaction = null;
    try {

      transaction = persistenceManager.currentTransaction();
      transaction.begin();
      SampleEntity sampleEntity = new SampleEntity();
      // with bean validation on, this works as expected when we fail to set the firstName
      sampleEntity.setFirstName("First");
      sampleEntity.setLastName("Last");
      System.out.println("make persistent");
      persistenceManager.makePersistent(sampleEntity);
      System.out.println("committing");
      transaction.commit();
      System.out.println("committed");

      JDOQLTypedQuery<SampleEntity> sampleEntityJDOQLTypedQuery =
          persistenceManager.newJDOQLTypedQuery(SampleEntity.class);
      QSampleEntity qSampleEntity = QSampleEntity.candidate();
      List<SampleEntity> results =
          sampleEntityJDOQLTypedQuery
              .filter(
                  qSampleEntity
                      .firstName
                      .startsWith("Fir")
                      .and(qSampleEntity.lastName.endsWith("st")))
              .executeList();

      System.out.println("results:" + results);
      results.forEach(result -> System.out.println("result:" + result));

      // QSample
      // sampleEntityJDOQLTypedQuery.candidate()
      //
      // sampleEntityJDOQLTypedQuery.filter(sampleEntityJDOQLTypedQuery.candidate().firstName.eq("First"))
      //            QSampleEntity cand = QProduct.candidate();
      //            List<Product> results =
      // sampleEntityJDOQLTypedQuery.filter(cand.value.lt(40.00).and(cand.name.startsWith("Wal")))
      //                    .executeList();
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      persistenceManager.close();
      persistenceManagerFactory.close();
    }
  }
}
