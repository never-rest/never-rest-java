package org.tosch.neverrest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

import java.util.Arrays;
import java.util.List;

public abstract class BaseCouchbaseConfig extends AbstractCouchbaseConfiguration {
    @Value("${couchbase.cluster.bucket}")
    private String bucketName;

    @Value("${couchbase.cluster.password}")
    private String password;

    @Value("${couchbase.cluster.hosts}")
    private String[] hosts;

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(hosts);
    }

    @Override
    protected String getBucketName() {
        return bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return password;
    }
}
