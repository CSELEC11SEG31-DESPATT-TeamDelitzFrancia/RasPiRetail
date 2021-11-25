package com.raspi.retail.backend.model.daos.query.store;

public enum UserDAOAdminQueryStore {
    GET_ALL_USERS("SELECT * FROM `RasPiRetail_Admins`"),
    GET_USER_BY_ID("SELECT * FROM `RasPiRetail_Admins` WHERE `id`=?"),
    GET_USER_BY_USERNAME("SELECT * FROM `RasPiRetail_Admins` WHERE `username` REGEXP ?"),
    POST_NEW_USER("INSERT INTO `RasPiRetail_Admins` " +
            "SET `username`=?, `password`=?"),
    PATCH_USER("UPDATE `RasPiRetail_Admins` " +
            "SET `username`=?, `password`=?"),
    DELETE_USER("DELETE FROM `RasPiRetail_Admins` WHERE `id`=?");

    private String query;

    UserDAOAdminQueryStore(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
