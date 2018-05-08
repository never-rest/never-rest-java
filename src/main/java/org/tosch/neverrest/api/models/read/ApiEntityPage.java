package org.tosch.neverrest.api.models.read;

import java.util.List;

public class ApiEntityPage<A extends ApiEntity<?, ?, ?, ?>> {
    private List<A> items;
    private long offset;
    private int limit;
    private long size;

    public List<A> getItems() {
        return items;
    }

    public void setItems(List<A> items) {
        this.items = items;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
