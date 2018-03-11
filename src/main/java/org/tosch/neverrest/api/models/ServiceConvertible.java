package org.tosch.neverrest.api.models;

public interface ServiceConvertible<S> {
    S toService();

    static <T extends ServiceConvertible<S>, S> S toService(T serviceConvertible) {
        if (serviceConvertible == null) {
            return null;
        }

        return serviceConvertible.toService();
    }
}
