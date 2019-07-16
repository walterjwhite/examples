package com.walterjwhite.examples.queue;

import com.google.inject.AbstractModule;
import com.walterjwhite.datastore.DefaultQueryBuilderResolver;
import com.walterjwhite.datastore.api.repository.QueryBuilderResolver;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.datastore.modules.GoogleGuicePersistModule;
import com.walterjwhite.datastore.modules.jpa.JpaRepository;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;
import com.walterjwhite.queue.jpa.guice.QueueModule;

public class QueueGuiceCLIModule extends AbstractModule implements GuiceApplicationModule {
  @Override
  protected void configure() {
    install(new QueueModule());
    install(new GoogleGuicePersistModule());
    bind(Repository.class).to(JpaRepository.class);
    bind(QueryBuilderResolver.class).to(DefaultQueryBuilderResolver.class);
    // install(new PrometheusMetricsModule());
  }
}
