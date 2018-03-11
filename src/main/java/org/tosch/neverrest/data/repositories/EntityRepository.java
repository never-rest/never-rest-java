package org.tosch.neverrest.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.tosch.neverrest.data.models.DataEntity;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface EntityRepository<D extends DataEntity<ID>, ID extends Serializable> extends CrudRepository<D, ID> {
    default Optional<D> findById(ID id) {
        return this.findById(id);
    }

    default D create(D dataEntity) {
        return save(dataEntity);
    }

    default D update(ID id, D dataEntity) {
        return save(dataEntity);
    }

    default boolean deleteIfExists(ID id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }

        return false;
    }
}
