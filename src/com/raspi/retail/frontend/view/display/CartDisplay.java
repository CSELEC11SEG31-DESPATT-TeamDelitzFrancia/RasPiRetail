package com.raspi.retail.frontend.view.display;

import com.raspi.retail.backend.model.dtos.CartItem;

import java.util.ArrayList;

public class CartDisplay {

    public static void displayUserCart(ArrayList<CartItem> userCart) {

        System.out.println("Cart: ");

        userCart.forEach(cartItem -> {
            System.out.print("    " + cartItem.getProductName() + "\t");
            System.out.print(cartItem.getQuantity() + "\t");
            System.out.print(cartItem.getSubtotal() + "    ");
            System.out.println();
        });

    }

}
