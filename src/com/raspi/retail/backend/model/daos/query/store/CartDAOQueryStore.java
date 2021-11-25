package com.raspi.retail.backend.model.daos.query.store;

public enum CartDAOQueryStore {
    GET_USER_CART("GET_USER_CART"),
    GET_USER_CART_ITEM("GET_USER_CART_ITEM"),
    POST_USER_CART_ITEM("POST_USER_CART_ITEM"),
    PUT_USER_CART_ITEM("PUT_USER_CART_ITEM"),
    DELETE_USER_CART_ITEM("DELETE_USER_CART_ITEM");

    private String queryType;

    CartDAOQueryStore(String queryType) {
        this.queryType = queryType;
    }

    public String getQueryType() {
        return this.queryType;
    }

}
