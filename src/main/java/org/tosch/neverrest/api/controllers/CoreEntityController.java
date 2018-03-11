package org.tosch.neverrest.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tosch.neverrest.api.models.create.CoreApiCreateEntity;
import org.tosch.neverrest.api.models.read.CoreApiEntity;
import org.tosch.neverrest.api.models.update.CoreApiUpdateEntity;
import org.tosch.neverrest.service.models.create.CoreServiceCreateEntity;
import org.tosch.neverrest.service.models.read.CoreServiceEntity;
import org.tosch.neverrest.service.models.update.CoreServiceUpdateEntity;
import org.tosch.neverrest.service.services.CoreEntityService;

import java.util.Set;
import java.util.UUID;

public abstract class CoreEntityController<
        A extends CoreApiEntity<A, AC, AU, S>,
        AC extends CoreApiCreateEntity<A, S, SC>,
        AU extends CoreApiUpdateEntity<A, S, SU>,
        S extends CoreServiceEntity<S, SC, SU, ?>,
        SC extends CoreServiceCreateEntity<S, ?>,
        SU extends CoreServiceUpdateEntity<S, ?>> extends EntityController<A, AC, AU, S, SC, SU> {
    public CoreEntityController(CoreEntityService<S, SC, SU, ?> coreEntityService) {
        super(coreEntityService);
        this.abstractCoreEntityService = coreEntityService;
    }

    private final CoreEntityService<S, SC, SU, ?> abstractCoreEntityService;

    protected boolean hasGetByUuid() {
        return true;
    }

    protected boolean hasCreate() {
        return true;
    }

    protected boolean hasUpdate() {
        return true;
    }

    protected boolean hasDelete() {
        return true;
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<A> getByUuid(@PathVariable UUID uuid,
                                    @RequestParam(required = false) Set<String> expand) {
        if (hasGetByUuid()) {
            S serviceEntity = abstractCoreEntityService.findByUuid(uuid);
            return prepareResponse(serviceEntity);
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<A> create(@RequestBody AC apiCreateEntity) {
        if (hasCreate()) {
            SC serviceCreateEntity = apiCreateEntity.toService();
            S serviceEntity = abstractCoreEntityService.create(serviceCreateEntity);
            return prepareResponse(serviceEntity, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<A> update(@PathVariable UUID uuid,
                                 @RequestBody AU apiUpdateEntity) {
        if (hasUpdate()) {
            SU serviceUpdateEntity = apiUpdateEntity.toService();
            S serviceEntity = abstractCoreEntityService.update(uuid, serviceUpdateEntity);
            return prepareResponse(serviceEntity);
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity<A> delete(@PathVariable UUID uuid) {
        if (hasDelete()) {
            if (abstractCoreEntityService.delete(uuid)) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }
}
