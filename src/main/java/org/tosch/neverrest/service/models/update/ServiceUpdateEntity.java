package org.tosch.neverrest.service.models.update;

import org.tosch.neverrest.data.models.DataEntity;
import org.tosch.neverrest.service.models.common.DataConvertible;
import org.tosch.neverrest.service.models.read.ServiceEntity;

public abstract class ServiceUpdateEntity<S extends ServiceEntity<S, ?, ?, D>, D extends DataEntity>
        implements DataConvertible<D> {
}
