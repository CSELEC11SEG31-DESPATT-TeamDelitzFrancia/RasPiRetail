package com.raspi.retail.backend.model.daos.query.store;

public enum PackageDAOQueryStore {
    GET_ALL_PACKAGES("GET_ALL_PACKAGES"),
    GET_PACKAGE_BY_ID("GET_PACKAGE_BY_ID"),
    POST_PACKAGE("POST_PACKAGE"),
    DELETE_PACKAGE("DELETE_PACKAGE");

    private String queryType;

    PackageDAOQueryStore(String queryType) {
        this.queryType = queryType;
    }

    public String getQueryType() {
        return this.queryType;
    }
}
