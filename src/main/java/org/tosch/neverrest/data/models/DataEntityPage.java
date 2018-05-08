package org.tosch.neverrest.data.models;

import java.io.Serializable;

public class DataEntityPage<D extends DataEntity> {
    private Iterable<D> items;
    private Integer offset;
    private Integer limit;
    private Long size;

    public Iterable<D> getItems() {
        return items;
    }

    public void setItems(Iterable<D> items) {
        this.items = items;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
