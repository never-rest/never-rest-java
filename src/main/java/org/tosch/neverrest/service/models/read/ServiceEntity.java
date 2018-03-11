package org.tosch.neverrest.service.models.read;

import org.tosch.neverrest.data.models.DataEntity;
import org.tosch.neverrest.service.models.create.ServiceCreateEntity;
import org.tosch.neverrest.service.models.update.ServiceUpdateEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ServiceEntity<
        S extends ServiceEntity<S, C, U, D>,
        C extends ServiceCreateEntity<S, D>,
        U extends ServiceUpdateEntity<S, D>,
        D extends DataEntity> {
    public ServiceEntity(D dataEntity) {

    }

    public static <S extends ServiceEntity<S, ?, ?, D>, D extends DataEntity> S fromData(
            D dataEntity, Class<S> serviceEntityClass) {
        if (dataEntity == null) {
            return null;
        }

        Constructor<? extends ServiceEntity<?, ?, ?, ?>> constructor = CONSTRUCTOR_MAP.get(serviceEntityClass);

        if (constructor == null) {
            try {
                constructor = serviceEntityClass.getConstructor(dataEntity.getClass());
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            CONSTRUCTOR_MAP.put(serviceEntityClass, constructor);
        }

        try {
            return (S) constructor.newInstance(dataEntity);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Map<Class<? extends ServiceEntity<?, ?, ?, ?>>,
            Constructor<? extends ServiceEntity<?, ?, ?, ?>>> CONSTRUCTOR_MAP = new ConcurrentHashMap<>();
}
