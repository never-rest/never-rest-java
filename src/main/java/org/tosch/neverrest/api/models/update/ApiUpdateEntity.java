package org.tosch.neverrest.api.models.update;

import org.tosch.neverrest.api.models.ServiceConvertible;
import org.tosch.neverrest.api.models.read.ApiEntity;
import org.tosch.neverrest.service.models.read.ServiceEntity;
import org.tosch.neverrest.service.models.update.ServiceUpdateEntity;

public abstract class ApiUpdateEntity<
        A extends ApiEntity<A, ?, ?, S>,
        S extends ServiceEntity<S, ?, ?, ?>,
        U extends ServiceUpdateEntity<S, ?>> implements ServiceConvertible<U> {
}
