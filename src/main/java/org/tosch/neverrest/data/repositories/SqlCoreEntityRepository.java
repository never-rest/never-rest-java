package org.tosch.neverrest.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.data.models.DataEntityPage;
import org.tosch.neverrest.data.models.OffsetLimitPageable;

import java.util.UUID;

@NoRepositoryBean
public interface SqlCoreEntityRepository<D extends CoreDataEntity<UUID>>
        extends CoreEntityRepository<D, UUID>, PagingAndSortingRepository<D, UUID> {
    default DataEntityPage<D> getPage(OffsetLimitPageable offsetLimit) {
        Page<D> dataPage = findAll(offsetLimit);
        DataEntityPage<D> coreDataEntityPage = new DataEntityPage<>();
        coreDataEntityPage.setOffset(offsetLimit.getOffset());
        coreDataEntityPage.setLimit(offsetLimit.getPageSize());
        coreDataEntityPage.setSize(dataPage.getTotalElements());
        coreDataEntityPage.setItems(dataPage.getContent());
        return coreDataEntityPage;
    }
}
