package org.tosch.neverrest.service.services.impl;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.data.models.CoreDataEntityPage;
import org.tosch.neverrest.data.repositories.CoreEntityRepository;
import org.tosch.neverrest.service.models.create.CoreServiceCreateEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntityPage;
import org.tosch.neverrest.service.models.update.CoreServiceUpdateEntity;
import org.tosch.neverrest.service.services.CoreEntityService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class CoreEntityServiceImpl<
        S extends CoreServiceEntity<S, C, U, D, ID>,
        C extends CoreServiceCreateEntity<S, D, ID>,
        U extends CoreServiceUpdateEntity<S, D, ID>,
        D extends CoreDataEntity<ID>,
        ID extends Serializable> implements CoreEntityService<S, C, U, D, ID> {
    public CoreEntityServiceImpl(CoreEntityRepository<D, ID> coreEntityRepository) {
        this.coreEntityRepository = coreEntityRepository;
    }

    private final CoreEntityRepository<D, ID> coreEntityRepository;

    @Override
    public S findByUuid(UUID uuid) {
        ID id = coreEntityRepository.parseUuid(uuid);
        Optional<D> dataEntity = coreEntityRepository.findById(id);

        if (!dataEntity.isPresent()) {
            return null;
        }

        return S.fromData(dataEntity.get(), getServiceEntityClass());
    }

    @Override
    public S create(C serviceCreateEntity) {
        D dataEntity = serviceCreateEntity.toData();
        dataEntity.setCreatedAt(DateTime.now(DateTimeZone.UTC));
        dataEntity.setModifiedAt(dataEntity.getCreatedAt());
        resolveRelationships(serviceCreateEntity, dataEntity);
        D createdDataEntity = coreEntityRepository.save(dataEntity);
        return S.fromData(createdDataEntity, getServiceEntityClass());
    }

    @Override
    public S update(UUID uuid, U serviceUpdateEntity) {
        ID id = coreEntityRepository.parseUuid(uuid);
        Optional<D> existingDataEntity = coreEntityRepository.findById(id);

        if (!existingDataEntity.isPresent()) {
            return null;
        }

        D dataEntity = serviceUpdateEntity.toData();
        updateEntity(existingDataEntity.get(), dataEntity);
        resolveRelationships(serviceUpdateEntity, dataEntity);
        D updatedDataEntity = coreEntityRepository.save(existingDataEntity.get());
        return S.fromData(updatedDataEntity, getServiceEntityClass());
    }

    @Override
    public Boolean delete(UUID uuid) {
        ID id = coreEntityRepository.parseUuid(uuid);
        Boolean exists = coreEntityRepository.existsById(id);

        if (exists) {
            coreEntityRepository.deleteById(id);
        }

        return exists;
    }

    protected void updateEntity(D entityToUpdate, D updateEntity) {
        entityToUpdate.setModifiedAt(DateTime.now(DateTimeZone.UTC));
    }

    protected CoreServiceEntityPage<S> getServiceEntityPage(CoreDataEntityPage<D, ID> coreDataEntityPage) {
        CoreServiceEntityPage<S> serviceEntityPage = new CoreServiceEntityPage<>();
        List<S> items = new ArrayList<>();
        coreDataEntityPage.getItems().forEach(i -> items.add(S.fromData(i, getServiceEntityClass())));
        serviceEntityPage.setItems(items);
        serviceEntityPage.setOffset(coreDataEntityPage.getOffset());
        serviceEntityPage.setLimit(coreDataEntityPage.getLimit());
        serviceEntityPage.setSize(coreDataEntityPage.getSize());
        return serviceEntityPage;
    }

    protected abstract Class<S> getServiceEntityClass();
    protected abstract void resolveRelationships(C serviceCreateEntity, D dataEntity);
    protected abstract void resolveRelationships(U serviceUpdateEntity, D dataEntity);
}
