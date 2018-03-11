package org.tosch.neverrest.data.models;

import org.springframework.data.couchbase.core.mapping.Document;

@Document
public abstract class CouchbaseCoreDataEntity extends CoreDataEntity<String> {
}