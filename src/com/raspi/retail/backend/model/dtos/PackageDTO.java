package com.raspi.retail.backend.model.dtos;

import com.raspi.retail.backend.model.dtos.enums.PackageType;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;

public class PackageDTO implements DTO {
    private int packageID;
    private int customerID;
    private PackageType packageType;
    private CustomerType customerType;
    private int totalQty;
    private double grandTotal;


    public PackageDTO(Builder builder) {
        this.packageID = builder.packageID;
        this.customerID = builder.customerID;
        this.packageType = builder.packageType;
        this.customerType = builder.customerType;
        this.totalQty = builder.totalQty;
        this.grandTotal = builder.grandTotal;
    }

    public PackageDTO() {}

    public int getPackageID() {
        return packageID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public static class Builder {
        private int packageID;
        private int customerID;
        private PackageType packageType;
        private CustomerType customerType;
        private int totalQty;
        private double grandTotal;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder setPackageID(int packageID) {
            this.packageID = packageID;
            return this;
        }

        public Builder setCustomerID(int customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder setPackageType(PackageType packageType) {
            this.packageType = packageType;
            return this;
        }

        public Builder setCustomerType(CustomerType customerType) {
            this.customerType = customerType;
            return this;
        }

        public Builder setTotalQty(int totalQty) {
            this.totalQty = totalQty;
            return this;
        }

        public Builder setGrandTotal(double grandTotal) {
            this.grandTotal = grandTotal;
            return this;
        }

        public PackageDTO pack() {
            return new PackageDTO(this);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
