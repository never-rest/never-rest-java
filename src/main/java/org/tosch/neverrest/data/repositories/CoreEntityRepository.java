package org.tosch.neverrest.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.tosch.neverrest.data.models.CoreDataEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface CoreEntityRepository<D extends CoreDataEntity<ID>, ID extends Serializable>
        extends CrudRepository<D, ID> {
}
