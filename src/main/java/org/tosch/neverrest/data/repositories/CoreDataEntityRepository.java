package org.tosch.neverrest.data.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.tosch.neverrest.data.models.CoreDataEntity;

import java.util.UUID;

@NoRepositoryBean
public interface CoreDataEntityRepository<D extends CoreDataEntity> extends DataEntityRepository<D, UUID> {
}
