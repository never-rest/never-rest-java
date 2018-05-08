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
        D extends DataEntity> implements EntityService<S, C, U, D> {
    protected ServiceEntityPage<S> getServiceEntityPage(DataEntityPage<D> coreDataEntityPage) {
        ServiceEntityPage<S> serviceEntityPage = new ServiceEntityPage<>();
        List<S> items = new ArrayList<>();
        coreDataEntityPage.getItems().forEach(i -> items.add(S.fromData(i, getServiceEntityClass())));
        serviceEntityPage.setItems(items);
        serviceEntityPage.setOffset(coreDataEntityPage.getOffset());
        serviceEntityPage.setLimit(coreDataEntityPage.getLimit());
        serviceEntityPage.setSize(coreDataEntityPage.getSize());
        return serviceEntityPage;
    }

    protected abstract Class<S> getServiceEntityClass();
}
