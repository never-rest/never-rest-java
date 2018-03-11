package org.tosch.neverrest.data.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.tosch.neverrest.data.models.CoreDataEntity;

@NoRepositoryBean
public interface CoreEntityRepository<D extends CoreDataEntity> extends EntityRepository<D, String> {
}
