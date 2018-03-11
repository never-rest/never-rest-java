package org.tosch.neverrest.api.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tosch.neverrest.api.models.read.ApiEntity;
import org.tosch.neverrest.api.models.read.ApiEntityPage;
import org.tosch.neverrest.api.models.create.ApiCreateEntity;
import org.tosch.neverrest.api.models.update.ApiUpdateEntity;
import org.tosch.neverrest.service.models.read.ServiceEntity;
import org.tosch.neverrest.service.models.read.ServiceEntityPage;
import org.tosch.neverrest.service.models.create.ServiceCreateEntity;
import org.tosch.neverrest.service.models.update.ServiceUpdateEntity;
import org.tosch.neverrest.service.services.EntityService;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class EntityController<
        A extends ApiEntity<A, AC, AU, S>,
        AC extends ApiCreateEntity<A, S, SC>,
        AU extends ApiUpdateEntity<A, S, SU>,
        S extends ServiceEntity<S, SC, SU, ?>,
        SC extends ServiceCreateEntity<S, ?>,
        SU extends ServiceUpdateEntity<S, ?>> {
    public EntityController(EntityService<S, SC, SU, ?> entityService) {
        this.EntityService = entityService;
    }

    private final EntityService<S, SC, SU, ?> EntityService;

    @Value("${org.tosch.api.base_url}")
    private String baseUrl;

    protected String getBaseUrl() {
        return baseUrl;
    }

    protected boolean hasGetPage() {
        return true;
    }

    protected abstract Class<A> getApiEntityClass();

    protected ResponseEntity<A> prepareResponse(S serviceEntity) {
        return prepareResponse(serviceEntity, HttpStatus.OK);
    }

    protected ResponseEntity<A> prepareResponse(S serviceEntity, HttpStatus httpStatus) {
        if (serviceEntity == null) {
            return ResponseEntity.notFound().build();
        }

        A apiEntity = A.fromService(baseUrl, getApiEntityClass(), serviceEntity);
        return ResponseEntity.status(httpStatus).body(apiEntity);
    }

    protected ResponseEntity<ApiEntityPage<A>> prepareResponse(ServiceEntityPage<S> serviceEntityPage) {
        ApiEntityPage<A> apiEntityPage = getApiEntityPage(serviceEntityPage);
        return ResponseEntity.ok(apiEntityPage);
    }

    private ApiEntityPage<A> getApiEntityPage(ServiceEntityPage<S> serviceEntityPage) {
        ApiEntityPage<A> apiEntityPage = new ApiEntityPage<>();
        apiEntityPage.setItems(serviceEntityPage.getItems().stream().map(serviceEntity ->
                A.fromService(baseUrl, getApiEntityClass(), serviceEntity)).collect(Collectors.toList()));
        apiEntityPage.setOffset(serviceEntityPage.getOffset());
        apiEntityPage.setLimit(serviceEntityPage.getLimit());
        apiEntityPage.setSize(serviceEntityPage.getSize());
        return apiEntityPage;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ApiEntityPage<A>> getPage(@RequestParam(required = false, defaultValue = "0") Integer offset,
                                  @RequestParam(required = false, defaultValue = "25") Integer limit,
                                  @RequestParam(required = false) Set<String> expand) {
        if (hasGetPage()) {
            ServiceEntityPage<S> serviceEntityPage = EntityService.getPage(offset, limit);
            return prepareResponse(serviceEntityPage);
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }
}
