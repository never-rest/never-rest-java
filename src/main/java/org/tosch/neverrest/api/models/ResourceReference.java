package org.tosch.neverrest.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;

import java.util.UUID;

public class ResourceReference implements ServiceConvertible<UUID> {
    public static ResourceReference build(UUID uuid, String apiEntityUrlPath, String baseUrl) {
        if (uuid == null) {
            return null;
        }

        return new ResourceReference(uuid, apiEntityUrlPath, baseUrl);
    }

    private ResourceReference(UUID uuid, String apiEntityUrlPath, String baseUrl) {
        this.apiEntityUrlPath = apiEntityUrlPath;
        href = String.format("%s/%s/%s", baseUrl, apiEntityUrlPath, uuid);
    }

    @JsonIgnore
    private final String apiEntityUrlPath;
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public UUID toService() {
        if (Strings.isNullOrEmpty(href)) {
            return null;
        }

        try {
            return UUID.fromString(href.substring(href.lastIndexOf("/" + apiEntityUrlPath + "/") + apiEntityUrlPath.length() + 2).replace("/", ""));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
