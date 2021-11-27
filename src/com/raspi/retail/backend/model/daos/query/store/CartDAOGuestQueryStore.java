package com.raspi.retail.backend.model.daos.query.store;

/**
 * SQL Queries for Guest Customer Carts
 */
public enum CartDAOGuestQueryStore {
    GET_USER_CART("SELECT * FROM `RasPiRetail_GuestsCart` WHERE `guestID`=?"),
    GET_USER_CART_ITEM("SELECT * FROM `RasPiRetail_GuestCustomerCarts` " +
                "WHERE `guestID`=? && `productID`=? "),
    POST_USER_CART_ITEM("INSERT INTO `RasPiRetail_GuestCustomerCarts` " +
                "SET `guestID`=?, `productID`=?, `quantity`=?"),
    PUT_USER_CART_ITEM("UPDATE `RasPiRetail_GuestCustomerCarts` " +
                "SET `quantity`=?, `dateUpdated`=CURRENT_TIMESTAMP " +
                "WHERE `guestID`=? && `productID`=?"),
    DELETE_USER_CART_ITEM("DELETE FROM `RasPiRetail_GuestCustomerCarts` " +
                "WHERE `guestID`=? && `productID`=? ");

    private String query;

    CartDAOGuestQueryStore(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
