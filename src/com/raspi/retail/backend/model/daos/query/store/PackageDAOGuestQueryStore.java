package com.raspi.retail.backend.model.daos.query.store;

public enum PackageDAOGuestQueryStore {
    GET_ALL_PACKAGES("SELECT * FROM `RasPiRetail_GuestsPackages`"),
    GET_PACKAGE_BY_ID("SELECT * FROM `RasPiRetail_GuestPackages` WHERE `packageID`=?"),
    POST_PACKAGE("INSERT INTO `RasPiRetail_GuestPackages` " +
            "SET `packageType`=?, `guestID`=?"),
    DELETE_PACKAGE("DELETE FROM `RasPiRetail_GuestPackages` WHERE `packageID`=?");

    private String query;

    PackageDAOGuestQueryStore(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
