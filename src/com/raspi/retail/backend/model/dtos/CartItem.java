package com.raspi.retail.backend.model.dtos;

public class CartItem {

    private int productID;
    private String productName;
    private int quantity;
    private double subtotal;

    public CartItem() {
        this.productID = -1;
        this.quantity = -1;
        this.subtotal = -1.0;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "[" + productID +
                "]: \"" + productName + "\"" +
                "_#" + hashCode() +
                '}';
    }
}
