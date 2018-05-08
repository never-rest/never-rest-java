package org.tosch.neverrest.data.models;

public class DataEntityPage<D extends DataEntity> {
    private Iterable<D> items;
    private long offset;
    private Integer limit;
    private long size;

    public Iterable<D> getItems() {
        return items;
    }

    public void setItems(Iterable<D> items) {
        this.items = items;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
