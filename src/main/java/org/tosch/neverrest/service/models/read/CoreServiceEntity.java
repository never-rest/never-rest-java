package org.tosch.neverrest.service.models.read;

import org.joda.time.DateTime;
import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.service.models.create.CoreServiceCreateEntity;
import org.tosch.neverrest.service.models.update.CoreServiceUpdateEntity;

import java.io.Serializable;
import java.util.UUID;

public abstract class CoreServiceEntity<
        S extends CoreServiceEntity<S, C, U, D, ID>,
        C extends CoreServiceCreateEntity<S, D, ID>,
        U extends CoreServiceUpdateEntity<S, D, ID>,
        D extends CoreDataEntity<ID>,
        ID extends Serializable> extends ServiceEntity<S, C, U, D> {
    public CoreServiceEntity(D coreDataEntity) {
        super(coreDataEntity);
        this.uuid = parseId(coreDataEntity.getId());
        this.createdAt = coreDataEntity.getCreatedAt();
        this.modifiedAt = coreDataEntity.getModifiedAt();
    }

    public abstract UUID parseId(ID id);

    private final UUID uuid;
    private final DateTime createdAt;
    private final DateTime modifiedAt;

    public UUID getUuid() {
        return uuid;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getModifiedAt() {
        return modifiedAt;
    }
}
