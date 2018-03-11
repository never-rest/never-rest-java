package org.tosch.neverrest.data.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public abstract class CoreDataEntity<ID extends Serializable> extends DataEntity {
    @Id
    private ID id;
    private DateTime createdAt;
    private DateTime modifiedAt;

    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }

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
