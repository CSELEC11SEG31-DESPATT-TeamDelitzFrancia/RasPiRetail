package com.raspi.retail.frontend.controller.middleware;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.CartDAO;
import com.raspi.retail.backend.model.dtos.CartItem;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.view.display.CartDisplay;

import java.util.ArrayList;
import java.util.Scanner;

public class CartController implements Facade {

    Scanner input = new Scanner(System.in);

    ModelFactory daoFactory = ModelFactoryProducer.createFactory("dao");
    CartDAO cDAO = (CartDAO) daoFactory.createDAOPrototype("cart");

    public void getUserCart(int userID, CustomerType userType) {
        ArrayList<CartItem> cart = cDAO.getUserCart(userID, userType);
        CartDisplay.displayUserCart(cart);
    }

    public void createOne(int userID, CustomerType userType) {
        int productID = 0;
        int qty = 0;

        productID = KBInput.readInt("Enter the Product ID: ");
        qty = KBInput.readInt("Enter the quantity: ");

        cDAO.addToCart(userID, productID, qty, userType);
    }

    public void updateOne(int userID, CustomerType userType) {
        int productID = 0;
        int newQty = 0;

        productID = KBInput.readInt("Enter the Product ID to update: ");
        newQty = KBInput.readInt("Enter the new quantity: ");

        cDAO.updateCartItemQuantity(userID, productID, newQty, userType);
    }

    public void deleteOne(int userID, CustomerType userType) {
        int productID = 0;

        productID = KBInput.readInt("Enter the Product ID to delete: ");
        cDAO.removeFromCart(userID, productID, userType);
    }


    // unused
    @Override
    public void viewAll() {

    }

    @Override
    public void createOne() {

    }

    @Override
    public void updateOne() {

    }

    @Override
    public void deleteOne() {

    }
}
