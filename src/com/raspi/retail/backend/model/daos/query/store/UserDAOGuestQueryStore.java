package com.raspi.retail.backend.model.daos.query.store;

public enum UserDAOGuestQueryStore {
    GET_ALL_USERS("SELECT * FROM `RasPiRetail_Guests` "),
    GET_USER_BY_ID("SELECT * FROM `RasPiRetail_Guests` WHERE `id`=?"),
    GET_USER_BY_EMAIL("SELECT * FROM `RasPiRetail_Guests` WHERE `email` REGEXP ?"),
    POST_NEW_USER("INSERT INTO `RasPiRetail_Guests` " +
            "SET `email`=?, `firstName`=?, `lastName`=?, `addressLine1`=?, `addressLine2`=?, `ccNo`=?"),
    PATCH_USER("UPDATE `RasPiRetail_Guests` " +
            "SET `email`=?, `firstName`=?, `lastName`=?, `addressLine1`=?, `addressLine2`=?, `ccNo`=?" +
            "WHERE `id`=?"),
    DELETE_USER("DELETE FROM `RasPiRetail_Guests` WHERE `id`=?");

    private String query;

    UserDAOGuestQueryStore(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }

}
