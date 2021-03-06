package com.raspi.retail.backend.model.daos.query.store;

/**
 * SQL Queries for Registered Customer Carts
 */
public enum CartDAOCustomerQueryStore {
    GET_USER_CART("SELECT * FROM `RasPiRetail_CustomersCart` WHERE `customerID`=?"),
    GET_USER_CART_ITEM("SELECT * FROM `RasPiRetail_RegisteredCustomerCarts` " +
                "WHERE customerID=? && productID=? "),
    POST_USER_CART_ITEM("INSERT INTO `RasPiRetail_RegisteredCustomerCarts` " +
                "SET `customerID`=?, `productID`=?, `quantity`=?"),
    PUT_USER_CART_ITEM("UPDATE `RasPiRetail_RegisteredCustomerCarts` " +
                "SET `quantity`=?, `dateUpdated`=CURRENT_TIMESTAMP " +
                "WHERE `customerID`=? && `productID`=?"),
    DELETE_USER_CART_ITEM("DELETE FROM `RasPiRetail_RegisteredCustomerCarts` " +
                "WHERE `customerID`=? && `productID`=? ");

    private String query;

    CartDAOCustomerQueryStore(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
