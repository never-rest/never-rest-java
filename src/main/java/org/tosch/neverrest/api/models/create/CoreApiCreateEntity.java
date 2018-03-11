package org.tosch.neverrest.api.models.create;

import org.tosch.neverrest.api.models.read.CoreApiEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;
import org.tosch.neverrest.service.models.create.CoreServiceCreateEntity;

public abstract class CoreApiCreateEntity<
        A extends CoreApiEntity<A, ?, ?, S>,
        S extends CoreServiceEntity<S, ?, ?, ?>,
        C extends CoreServiceCreateEntity<S, ?>>
        extends ApiCreateEntity<A, S, C> {
}
