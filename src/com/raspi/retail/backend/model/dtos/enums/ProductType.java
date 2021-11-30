package com.raspi.retail.backend.model.dtos.enums;

public enum ProductType {
    DEFAULT(""), MAINLINE("Mainline"), MICRO("Micro");

    private String productType;

    ProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return this.productType;
    }

    @Override
    public String toString() {
        return this.productType;
    }
}
