package com.walterjwhite.examples.index;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.datastore.query.entityType.FindEntityTypeByNameQuery;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.query.FieldType;
import com.walterjwhite.index.api.model.query.MatchType;
import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.model.query.predicate.AttributePredicate;
import com.walterjwhite.index.api.service.IndexSearchService;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.transaction.Transactional;

public class IndexExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final Repository repository;
  protected final IndexSearchService indexSearchService;

  @Inject
  public IndexExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      Repository repository,
      IndexSearchService indexSearchService) {
    super(shutdownTimeoutInSeconds);
    this.repository = repository;
    this.indexSearchService = indexSearchService;
  }

  @Transactional
  public void createEntity() {
    IndexExampleEntity indexExampleEntity = new IndexExampleEntity();
    indexExampleEntity.setDateTime(LocalDateTime.now());
    indexExampleEntity.setName("some name");
    indexExampleEntity.setFavoriteNumber(527);

    repository.create(indexExampleEntity);
  }

  public void search() throws Exception {
    // SearchQuery searchQuery = new SearchQuery(new Index("default"), repository.query(new
    // FindEntityTypeByNameQueryConfiguration(IndexExampleEntity.class.getSimpleName())), null);
    SearchQuery searchQuery =
        new SearchQuery(
            new Index("default"),
            repository.query(
                new FindEntityTypeByNameQuery(IndexExampleEntity.class.getSimpleName())),
            new AttributePredicate(FieldType.Text, MatchType.Like, "name", "some"));
    indexSearchService.search(searchQuery);

    System.out.println("result size:" + searchQuery.getResultSize());
    System.out.println("execution time:" + searchQuery.getExecutionTime());
    searchQuery
        .getIndexRecords()
        .forEach(indexRecord -> System.out.println("Search Record:" + indexRecord));
  }

  @Override
  protected void doRun(String... arguments) {
    // index should be created at this point
    createEntity();

    // wait a little bit to let the indexing take place

    // step 2: index a CSV file, illustrate searching across fields ...
  }
}
