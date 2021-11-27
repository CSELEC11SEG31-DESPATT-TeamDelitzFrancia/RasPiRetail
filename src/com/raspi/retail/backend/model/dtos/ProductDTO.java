package com.raspi.retail.backend.model.dtos;

import com.raspi.retail.backend.model.dtos.enums.ProductType;

public class ProductDTO implements DTO {

    // Product Variables
    int id;
    String name;
    double price;
    int stock;
    ProductType productType;
    String description;
    String urlListing;

    // set default values as constructor
    public ProductDTO() {
        this.id = -1;
        this.name = "";
        this.stock = -1;
        this.price = -1.0;
        this.productType = ProductType.DEFAULT;
        this.description = "";
        this.urlListing = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProductType() {
        return productType.getProductType();
    }

    public void setProductType(String productType) {
        this.productType = ProductType.valueOf(productType.toUpperCase());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlListing() {
        return urlListing;
    }

    public void setUrlListing(String urlListing) {
        this.urlListing = urlListing;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "[" + id +
                "]: \"" + name + '"' +
                "_#" + hashCode() +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
