package org.tosch.neverrest.data.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.tosch.neverrest.data.models.CoreDataEntity;

@NoRepositoryBean
public interface CouchbaseCoreEntityRepository<D extends CoreDataEntity<String>>
        extends CoreEntityRepository<D, String> {

}