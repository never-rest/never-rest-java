package org.tosch.neverrest.service.models.update;

import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;

import java.io.Serializable;

public abstract class CoreServiceUpdateEntity<S extends CoreServiceEntity<S, ?, ?, D, ? extends Serializable>, D extends CoreDataEntity<? extends Serializable>>
        extends ServiceUpdateEntity<S, D> {
}
