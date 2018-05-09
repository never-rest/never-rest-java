package org.tosch.neverrest.api.models.read;

import org.tosch.neverrest.api.models.create.CoreApiCreateEntity;
import org.tosch.neverrest.api.models.update.CoreApiUpdateEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;

import java.util.Date;

public abstract class CoreApiEntity<
        A extends CoreApiEntity<A, C, U, S>,
        C extends CoreApiCreateEntity<A, S, ?>,
        U extends CoreApiUpdateEntity<A, S, ?>,
        S extends CoreServiceEntity<S, ?, ?, ?, ?>> extends ApiEntity<A, C, U, S> {
    public CoreApiEntity(String baseUrl, S serviceEntity) {
        super(baseUrl, serviceEntity);
        this.href = String.format("%s/%s/%s", baseUrl, getApiEntityPath(), serviceEntity.getUuid());
        this.createdAt = serviceEntity.getCreatedAt();
        this.modifiedAt = serviceEntity.getModifiedAt();
    }

    private final String href;
    private final Date createdAt;
    private final Date modifiedAt;

    public String getHref() {
        return href;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }
}
