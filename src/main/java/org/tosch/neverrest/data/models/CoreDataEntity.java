package org.tosch.neverrest.data.models;

import java.util.UUID;

import org.joda.time.DateTime;

public abstract class CoreDataEntity extends DataEntity<UUID> {
    private DateTime createdAt;
    private DateTime modifiedAt;

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(DateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
