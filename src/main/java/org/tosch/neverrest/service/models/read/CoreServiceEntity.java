package org.tosch.neverrest.service.models.read;

import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.service.models.create.CoreServiceCreateEntity;
import org.tosch.neverrest.service.models.update.CoreServiceUpdateEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class CoreServiceEntity<
        S extends CoreServiceEntity<S, C, U, D, ID>,
        C extends CoreServiceCreateEntity<S, D>,
        U extends CoreServiceUpdateEntity<S, D>,
        D extends CoreDataEntity<ID>,
        ID extends Serializable> extends ServiceEntity<S, C, U, D> {
    public CoreServiceEntity(D coreDataEntity) {
        super(coreDataEntity);
        this.uuid = parseId(coreDataEntity.getId());
        this.createdAt = coreDataEntity.getCreatedAt();
        this.modifiedAt = coreDataEntity.getUpdatedAt();
    }

    public abstract UUID parseId(ID id);

    private final UUID uuid;
    private final Date createdAt;
    private final Date modifiedAt;

    public UUID getUuid() {
        return uuid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }
}
