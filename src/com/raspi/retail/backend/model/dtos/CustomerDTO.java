package com.raspi.retail.backend.model.dtos;

import java.util.ArrayList;

public class CustomerDTO extends UserDTO {

    protected String email;
    protected String firstName;
    protected String lastName;
    protected String addressLine1;
    protected String addressLine2;
    protected CreditCard ccNo;
    protected ArrayList<CartItem> cart;

}
