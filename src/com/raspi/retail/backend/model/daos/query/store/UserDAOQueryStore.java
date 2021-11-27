package com.raspi.retail.backend.model.daos.query.store;

public enum UserDAOQueryStore {
    GET_ALL_USERS("GET_ALL_USERS"),
    GET_USER_BY_ID("GET_USER_BY_ID"),
    GET_USER_BY_USERNAME("GET_USER_BY_USERNAME"),
    GET_USER_BY_EMAIL("GET_USER_BY_EMAIL"),
    POST_NEW_USER("POST_NEW_USER"),
    PATCH_USER("PATCH_USER"),
    DELETE_USER("DELETE_USER");

    private String queryType;

    UserDAOQueryStore(String queryType) {
        this.queryType = queryType;
    }

    public String getQueryType() {
        return this.queryType;
    }
}
