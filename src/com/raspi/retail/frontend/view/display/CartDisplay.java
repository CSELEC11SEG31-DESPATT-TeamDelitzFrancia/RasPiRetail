package com.raspi.retail.frontend.view.display;

import com.raspi.retail.backend.model.dtos.CartItem;

import java.util.Iterator;

public class CartDisplay {

    public static void displayUserCart(Iterator<CartItem> userCart) {
        if(userCart.hasNext()) {
            System.out.println("Cart: ");
            userCart.forEachRemaining(cartItem -> {
                System.out.print("    " + cartItem.getProductName() + "\t");
                System.out.print(cartItem.getQuantity() + "\t");
                System.out.print(cartItem.getSubtotal() + "    ");
                System.out.println();
            });
        } else {
            System.out.println("No Items in cart.");
        }
    }
}
