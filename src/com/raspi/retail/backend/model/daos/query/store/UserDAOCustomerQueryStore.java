package com.raspi.retail.backend.model.daos.query.store;

public enum UserDAOCustomerQueryStore {
    GET_ALL_USERS("SELECT * FROM `RasPiRetail_Customers`"),
    GET_USER_BY_ID("SELECT * FROM `RasPiRetail_Customers` WHERE `id`=?"),
    GET_USER_BY_USERNAME("SELECT * FROM `RasPiRetail_Customers` WHERE `username` REGEXP ?"),
    GET_USER_BY_EMAIL("SELECT * FROM `RasPiRetail_Customers` WHERE `email` REGEXP ?"),
    POST_NEW_USER("INSERT INTO `RasPiRetail_Customers` " +
            "SET `username`=?, `password`=?, `email`=?, `firstName`=?, `lastName`=?, `addressLine1`=?, `addressLine2`=?, `ccNo`=?"),
    PATCH_USER("UPDATE `RasPiRetail_Customers` " +
            "SET `username`=?, `password`=?, `email`=?, `firstName`=?, `lastName`=?, `addressLine1`=?, `addressLine2`=?, `ccNo`=?"),
    DELETE_USER("DELETE FROM `RasPiRetail_Customers` WHERE `id`=?");

    private String query;

    UserDAOCustomerQueryStore(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }

}
