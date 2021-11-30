package com.raspi.retail.backend.model.dtos.enums;

public enum PackageType {
    DEFAULT(""),
    STANDARD_ELECTROSTATIC("STANDARD_ELECTROSTATIC"),
    BUBBLEWRAP_ELECTROSTATIC("BUBBLEWRAP_ELECTROSTATIC");

    private String packageType;

    PackageType(String packageType) { this.packageType = packageType; }
    public String getProductType() {
        return this.packageType;
    }

    @Override
    public String toString() {
        return this.packageType;
    }
}
