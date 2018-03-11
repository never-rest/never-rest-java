package org.tosch.neverrest.service.models.common;

import org.tosch.neverrest.data.models.DataEntity;

public interface DataConvertible<D extends DataEntity<?>> {
    D toData();
}
