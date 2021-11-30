package com.raspi.retail.backend.model.daos.query.store;

public enum PackageDAOCustomerQueryStore {
    GET_ALL_PACKAGES("SELECT * FROM `RasPiRetail_CustomersPackages`"),
    GET_PACKAGE_BY_ID("SELECT * FROM `RasPiRetail_CustomerPackages` WHERE `packageID`=?"),
    POST_PACKAGE("INSERT INTO `RasPiRetail_CustomerPackages` " +
            "SET `packageType`=?, `customerID`=?"),
    DELETE_PACKAGE("DELETE FROM `RasPiRetail_CustomerPackages` WHERE `packageID`=?");

    private String query;

    PackageDAOCustomerQueryStore(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
