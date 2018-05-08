package org.tosch.neverrest.service.services;

import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.service.models.create.CoreServiceCreateEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;
import org.tosch.neverrest.service.models.update.CoreServiceUpdateEntity;

import java.io.Serializable;
import java.util.UUID;

public interface CoreEntityService<
        S extends CoreServiceEntity<S, C, U, D>,
        C extends CoreServiceCreateEntity<S, D>,
        U extends CoreServiceUpdateEntity<S, D>,
        D extends CoreDataEntity<ID>,
        ID extends Serializable> extends EntityService<S, C, U, D> {
    S findByUuid(UUID uuid);
    S create(C serviceCreateEntity);
    S update(UUID uuid, U serviceUpdateEntity);
    Boolean delete(UUID uuid);
}
