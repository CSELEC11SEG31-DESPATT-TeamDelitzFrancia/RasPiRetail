package com.raspi.retail.backend.model.daos.query.store;

/**
 * SQL Queries for Product CRUD (Create, Read, Update, Delete)
 */
public enum ProductDAOQueryStore {
    GET_ALL("SELECT * FROM `RasPiRetail_Products`"),
    GET_ONE_BY_ID("SELECT * FROM `RasPiRetail_Products` WHERE `id` = ?"),
    GET_ONE_BY_NAME("SELECT * FROM `RasPiRetail_Products` WHERE `name` REGEXP ?"),
    GET_ONE_BY_TYPE("SELECT * FROM `RasPiRetail_Products` WHERE `type` REGEXP ?"),
    POST_ONE("INSERT INTO `RasPiRetail_Products` " +
                "SET `name`=?, `stock`=?, `price`=?, `type`=?, `description`=?, `url`=?"),
    PATCH_ONE("UPDATE `RasPiRetail_Products` " +
                "SET `name`=?, `stock`=?, `price`=?, `type`=?, `description`=?, `url`=? " +
                "WHERE `id`=?"),
    DELETE_ONE("DELETE FROM `RasPiRetail_Products` WHERE `id`=?");

    private String query;

    ProductDAOQueryStore(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }


}
