package org.tosch.neverrest.api.models.read;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.tosch.neverrest.api.models.create.ApiCreateEntity;
import org.tosch.neverrest.api.models.update.ApiUpdateEntity;
import org.tosch.neverrest.service.models.read.ServiceEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ApiEntity<
        A extends ApiEntity<A, C, U, S>,
        C extends ApiCreateEntity<A, S, ?>,
        U extends ApiUpdateEntity<A, S, ?>,
        S extends ServiceEntity<S, ?, ?, ?>> {
    public ApiEntity(String baseUrl, S serviceEntity) {
        super();
    }

    @JsonIgnore
    public abstract String getApiEntityPath();
    protected abstract Class<A> getApiEntityClass();

    public static <A extends ApiEntity<A, ?, ?, S>,
            S extends ServiceEntity<S, ?, ?, ?>> A fromService(
            String baseUrl, Class<A> apiEntityClass, S serviceEntity) {
        if (serviceEntity == null) {
            return null;
        }

        Constructor<? extends ApiEntity<?, ?, ?, ?>> constructor = CONSTRUCTOR_MAP.get(apiEntityClass);

        if (constructor == null) {
            try {
                constructor = apiEntityClass.getConstructor(String.class, serviceEntity.getClass());
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            CONSTRUCTOR_MAP.put(apiEntityClass, constructor);
        }

        try {
            return (A) constructor.newInstance(baseUrl, serviceEntity);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Map<Class<? extends ApiEntity<?, ?, ?, ?>>,
            Constructor<? extends ApiEntity<?, ?, ?, ?>>> CONSTRUCTOR_MAP = new ConcurrentHashMap<>();
}
