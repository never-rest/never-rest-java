package org.tosch.neverrest.api.models.update;

import org.tosch.neverrest.api.models.read.CoreApiEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;
import org.tosch.neverrest.service.models.update.CoreServiceUpdateEntity;

public abstract class CoreApiUpdateEntity<
        A extends CoreApiEntity<A, ?, ?, S>,
        S extends CoreServiceEntity<S, ?, ?, ?, ?>,
        U extends CoreServiceUpdateEntity<S, ?>>
        extends ApiUpdateEntity<A, S, U> {
}
