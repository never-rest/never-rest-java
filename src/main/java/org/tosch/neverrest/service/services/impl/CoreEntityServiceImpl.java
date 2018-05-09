package org.tosch.neverrest.service.services.impl;

import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.data.repositories.CoreEntityRepository;
import org.tosch.neverrest.service.models.create.CoreServiceCreateEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;
import org.tosch.neverrest.service.models.update.CoreServiceUpdateEntity;
import org.tosch.neverrest.service.services.CoreEntityService;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public abstract class CoreEntityServiceImpl<
        S extends CoreServiceEntity<S, C, U, D, ?>,
        C extends CoreServiceCreateEntity<S, D>,
        U extends CoreServiceUpdateEntity<S, D>,
        D extends CoreDataEntity<ID>,
        ID extends Serializable> extends EntityServiceImpl<S, C, U, D>  implements CoreEntityService<S, C, U, D, ID> {
    public CoreEntityServiceImpl(CoreEntityRepository<D, ID> coreEntityRepository) {
        this.coreEntityRepository = coreEntityRepository;
    }

    private final CoreEntityRepository<D, ID> coreEntityRepository;

    @Override
    public S findByUuid(UUID uuid) {
        ID id = parseUuid(uuid);
        Optional<D> dataEntity = coreEntityRepository.findById(id);

        if (!dataEntity.isPresent()) {
            return null;
        }

        return S.fromData(dataEntity.get(), getServiceEntityClass());
    }

    @Override
    public S create(C serviceCreateEntity) {
        D dataEntity = serviceCreateEntity.toData();
        dataEntity.setCreatedAt(Date.from(Instant.now(Clock.systemUTC())));
        dataEntity.setUpdatedAt(dataEntity.getCreatedAt());
        resolveRelationships(serviceCreateEntity, dataEntity);
        D createdDataEntity = coreEntityRepository.save(dataEntity);
        return S.fromData(createdDataEntity, getServiceEntityClass());
    }

    @Override
    public S update(UUID uuid, U serviceUpdateEntity) {
        ID id = parseUuid(uuid);
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
        ID id = parseUuid(uuid);
        Boolean exists = coreEntityRepository.existsById(id);

        if (exists) {
            coreEntityRepository.deleteById(id);
        }

        return exists;
    }

    protected void updateEntity(D entityToUpdate, D updateEntity) {
        entityToUpdate.setUpdatedAt(Date.from(Instant.now(Clock.systemUTC())));
    }

    protected abstract void resolveRelationships(C serviceCreateEntity, D dataEntity);
    protected abstract void resolveRelationships(U serviceUpdateEntity, D dataEntity);
}
