package org.tosch.neverrest.service.models.common;

import java.io.Serializable;

import org.tosch.neverrest.data.models.DataEntity;

public interface DataConvertible<D extends DataEntity<?>> {
    D toData();
}
