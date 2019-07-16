package com.walterjwhite.examples.index;

import com.google.inject.AbstractModule;
import com.walterjwhite.datastore.DefaultQueryBuilderResolver;
import com.walterjwhite.datastore.api.repository.QueryBuilderResolver;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.datastore.modules.jpa.JpaRepository;
import com.walterjwhite.encryption.modules.guice.EncryptionModule;
import com.walterjwhite.index.providers.guice.ElasticSearchModule;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;
import com.walterjwhite.serialization.jackson.guice.JacksonSerializationServiceModule;

public class GuiceIndexExampleModule extends AbstractModule implements GuiceApplicationModule {

  @Override
  protected void configure() {
    //    install(new CriteriaBuilderModule());
    //    install(new GoogleGuicePersistModule(/*propertyManager, reflections*/ ));
    install(new EncryptionModule());

    // this should be in the module, not here ...
    bind(Repository.class).to(JpaRepository.class);
    bind(QueryBuilderResolver.class).to(DefaultQueryBuilderResolver.class);

    // bind(EntityCreator.class);

    // put this in elastic search module?
    install(new JacksonSerializationServiceModule());
    install(new ElasticSearchModule());

    //    install(new JPAEventModule());
    //    // install(new QueueModule());
    //    install(new JPAIndexModule());
  }
}
