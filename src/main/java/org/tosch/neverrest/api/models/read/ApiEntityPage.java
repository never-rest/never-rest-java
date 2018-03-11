package org.tosch.neverrest.api.models.read;

import java.util.List;

public class ApiEntityPage<A extends ApiEntity> {
    private List<A> items;
    private Integer offset;
    private Integer limit;
    private Long size;

    public List<A> getItems() {
        return items;
    }

    public void setItems(List<A> items) {
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
