package com.raspi.retail.backend.model.dtos.customer;

import com.raspi.retail.backend.model.dtos.CartItem;
import com.raspi.retail.backend.model.dtos.CreditCard;
import com.raspi.retail.backend.model.dtos.CustomerDTO;

import java.util.ArrayList;

public class MemberDTO extends CustomerDTO {

    private int id;

    public MemberDTO() {
        this.id = -1;
        super.username = "";
        super.password = "";
        super.email = "";
        super.firstName = "";
        super.lastName = "";
        super.addressLine1 = "";
        super.addressLine2 = "";
        super.ccNo = new CreditCard();
        super.cart = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return super.username;
    }

    public void setUsername(String username) {
        super.username = username;
    }

    public String getPassword() {
        return super.password;
    }

    public void setPassword(String password) {
        super.password = password;
    }

    public String getEmail() {
        return super.email;
    }

    public void setEmail(String email) {
        super.email = email;
    }

    public String getFirstName() {
        return super.firstName;
    }

    public void setFirstName(String firstName) {
        super.firstName = firstName;
    }

    public String getLastName() {
        return super.lastName;
    }

    public void setLastName(String lastName) {
        super.lastName = lastName;
    }

    public String getAddressLine1() {
        return super.addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        super.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return super.addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        super.addressLine2 = addressLine2;
    }

    public CreditCard getCcNo() {
        return super.ccNo;
    }

    public void setCcNo(CreditCard ccNo) {
        super.ccNo = ccNo;
    }

    public ArrayList<CartItem> getCart() {
        return super.cart;
    }

    public void setCart(ArrayList<CartItem> cart) {
        super.cart = cart;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
