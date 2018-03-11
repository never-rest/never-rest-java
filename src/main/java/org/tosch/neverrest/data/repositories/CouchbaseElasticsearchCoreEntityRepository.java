package org.tosch.neverrest.data.repositories;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.repository.NoRepositoryBean;
import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.data.models.CoreDataEntityPage;

import java.util.ArrayList;
import java.util.List;

@NoRepositoryBean
public interface CouchbaseElasticsearchCoreEntityRepository<D extends CoreDataEntity<String>> extends CouchbaseCoreEntityRepository<D> {
    default CoreDataEntityPage<D, String> getPage(Client client, String index, int offset, int limit) {
        SearchResponse searchResponse = client.prepareSearch(index)
                .setTypes("couchbaseDocument")
                .setFrom(offset)
                .setSize(limit)
                .execute().actionGet();

        List<String> ids = new ArrayList<>();

        for (SearchHit searchHitFields : searchResponse.getHits()) {
            ids.add(searchHitFields.getId());
        }

        CoreDataEntityPage<D, String> coreDataEntityPage = new CoreDataEntityPage<>();
        coreDataEntityPage.setOffset(offset);
        coreDataEntityPage.setLimit(limit);
        coreDataEntityPage.setSize(searchResponse.getHits().totalHits());
        coreDataEntityPage.setItems(findAllById(ids));
        return coreDataEntityPage;
    }
}
