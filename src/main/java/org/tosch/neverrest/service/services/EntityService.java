package org.tosch.neverrest.service.services;

import org.tosch.neverrest.data.models.DataEntity;
import org.tosch.neverrest.service.models.create.ServiceCreateEntity;
import org.tosch.neverrest.service.models.read.ServiceEntity;
import org.tosch.neverrest.service.models.read.ServiceEntityPage;
import org.tosch.neverrest.service.models.update.ServiceUpdateEntity;

public interface EntityService<
        S extends ServiceEntity<S, C, U, D>,
        C extends ServiceCreateEntity<S, D>,
        U extends ServiceUpdateEntity<S, D>,
        D extends DataEntity> {
    ServiceEntityPage<S> getPage(long offset, int limit);
}
