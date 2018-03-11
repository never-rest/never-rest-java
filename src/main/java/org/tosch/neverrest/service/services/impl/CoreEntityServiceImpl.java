package org.tosch.neverrest.service.services.impl;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.data.repositories.CoreEntityRepository;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;
import org.tosch.neverrest.service.models.create.CoreServiceCreateEntity;
import org.tosch.neverrest.service.models.update.CoreServiceUpdateEntity;
import org.tosch.neverrest.service.services.CoreEntityService;
import java.util.Optional;
import java.util.UUID;

public abstract class CoreEntityServiceImpl<
        S extends CoreServiceEntity<S, C, U, D>,
        C extends CoreServiceCreateEntity<S, D>,
        U extends CoreServiceUpdateEntity<S, D>,
        D extends CoreDataEntity> extends EntityServiceImpl<S, C, U, D> implements CoreEntityService<S, C, U, D> {
    public CoreEntityServiceImpl(CoreEntityRepository<D> coreEntityRepository) {
        this.coreEntityRepository = coreEntityRepository;
    }

    private final CoreEntityRepository<D> coreEntityRepository;

    @Override
    public S findByUuid(UUID uuid) {
        Optional<D> dataEntity = coreEntityRepository.findById(uuid.toString());

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
        D createdDataEntity = coreEntityRepository.create(dataEntity);
        return S.fromData(createdDataEntity, getServiceEntityClass());
    }

    @Override
    public S update(UUID uuid, U serviceUpdateEntity) {
        Optional<D> existingDataEntity = coreEntityRepository.findById(uuid.toString());

        if (!existingDataEntity.isPresent()) {
            return null;
        }

        D dataEntity = serviceUpdateEntity.toData();
        updateEntity(existingDataEntity.get(), dataEntity);
        resolveRelationships(serviceUpdateEntity, dataEntity);
        D updatedDataEntity = coreEntityRepository.update(uuid.toString(), existingDataEntity.get());
        return S.fromData(updatedDataEntity, getServiceEntityClass());
    }

    @Override
    public Boolean delete(UUID uuid) {
        return coreEntityRepository.deleteIfExists(uuid.toString());
    }

    @Override
    protected void updateEntity(D entityToUpdate, D updateEntity) {
        entityToUpdate.setModifiedAt(DateTime.now(DateTimeZone.UTC));
    }
}
