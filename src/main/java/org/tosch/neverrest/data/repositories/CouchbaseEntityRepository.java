package org.tosch.neverrest.data.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.tosch.neverrest.data.models.DataEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface CouchbaseEntityRepository<D extends DataEntity<ID>, ID extends Serializable> extends EntityRepository<D, ID> {
}