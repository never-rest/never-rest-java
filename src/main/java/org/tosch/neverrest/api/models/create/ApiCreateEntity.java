package org.tosch.neverrest.api.models.create;

import org.tosch.neverrest.api.models.ServiceConvertible;
import org.tosch.neverrest.api.models.read.ApiEntity;
import org.tosch.neverrest.service.models.read.ServiceEntity;
import org.tosch.neverrest.service.models.create.ServiceCreateEntity;

public abstract class ApiCreateEntity<
        A extends ApiEntity<A, ?, ?, S>,
        S extends ServiceEntity<S, ?, ?, ?>,
        C extends ServiceCreateEntity<S, ?>> implements ServiceConvertible<C> {
}
