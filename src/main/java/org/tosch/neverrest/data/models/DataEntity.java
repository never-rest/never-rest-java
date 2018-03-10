package org.tosch.neverrest.data.models;

import java.io.Serializable;
import org.springframework.data.annotation.Id;

public abstract class DataEntity<ID extends Serializable> {
    @Id
    private ID id;

    public ID getUuid() {
        return this.id;
    }

    public void setUuid(ID id) {
        this.id = id;
    }
}