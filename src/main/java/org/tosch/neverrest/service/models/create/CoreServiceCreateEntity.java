package org.tosch.neverrest.service.models.create;

import org.tosch.neverrest.data.models.CoreDataEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;

import java.io.Serializable;

public abstract class CoreServiceCreateEntity<S extends CoreServiceEntity<S, ?, ?, D>, D extends CoreDataEntity<? extends Serializable>>
        extends ServiceCreateEntity<S, D> {
}
