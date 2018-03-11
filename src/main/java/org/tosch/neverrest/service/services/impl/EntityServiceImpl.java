package org.tosch.neverrest.service.services.impl;

import org.tosch.neverrest.data.models.DataEntity;
import org.tosch.neverrest.data.models.DataEntityPage;
import org.tosch.neverrest.service.models.create.ServiceCreateEntity;
import org.tosch.neverrest.service.models.read.ServiceEntity;
import org.tosch.neverrest.service.models.read.ServiceEntityPage;
import org.tosch.neverrest.service.models.update.ServiceUpdateEntity;
import org.tosch.neverrest.service.services.EntityService;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityServiceImpl<
        S extends ServiceEntity<S, C, U, D>,
        C extends ServiceCreateEntity<S, D>,
        U extends ServiceUpdateEntity<S, D>,
        D extends DataEntity<?>> implements EntityService<S, C, U, D> {
    protected ServiceEntityPage<S> getServiceEntityPage(DataEntityPage<D, ?> dataEntityPage) {
        ServiceEntityPage<S> serviceEntityPage = new ServiceEntityPage<>();
        List<S> items = new ArrayList<>();
        dataEntityPage.getItems().forEach(i -> items.add(S.fromData(i, getServiceEntityClass())));
        serviceEntityPage.setItems(items);
        serviceEntityPage.setOffset(dataEntityPage.getOffset());
        serviceEntityPage.setLimit(dataEntityPage.getLimit());
        serviceEntityPage.setSize(dataEntityPage.getSize());
        return serviceEntityPage;
    }

    protected abstract Class<S> getServiceEntityClass();
    protected abstract void updateEntity(D entityToUpdate, D updateEntity);
    protected abstract void resolveRelationships(C serviceCreateEntity, D dataEntity);
    protected abstract void resolveRelationships(U serviceUpdateEntity, D dataEntity);
}
