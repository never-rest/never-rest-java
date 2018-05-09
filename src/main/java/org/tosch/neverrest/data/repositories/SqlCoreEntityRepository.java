package org.tosch.neverrest.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.data.models.DataEntityPage;
import org.tosch.neverrest.data.models.OffsetLimitPageable;

import java.io.Serializable;

@NoRepositoryBean
public interface SqlCoreEntityRepository<D extends CoreDataEntity<ID>, ID extends Serializable>
        extends CoreEntityRepository<D, ID>, PagingAndSortingRepository<D, ID> {
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
