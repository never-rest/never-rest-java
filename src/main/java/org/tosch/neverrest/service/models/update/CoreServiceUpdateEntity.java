package org.tosch.neverrest.service.models.update;

import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;

public abstract class CoreServiceUpdateEntity<S extends CoreServiceEntity<S, ?, ?, D>, D extends CoreDataEntity>
        extends ServiceUpdateEntity<S, D> {
}
