package com.raspi.retail.frontend.controller.middleware;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.CartDAO;
import com.raspi.retail.backend.model.dtos.CartItem;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.frontend.view.display.CartDisplay;

import java.util.ArrayList;

public class CartController implements Facade {

    ModelFactory daoFactory = ModelFactoryProducer.createFactory("dao");
    CartDAO cDAO = (CartDAO) daoFactory.createDAOPrototype("cart");

    public void getUserCart(int userID, CustomerType userType) {
        ArrayList<CartItem> cart = cDAO.getUserCart(userID, userType);
        CartDisplay.displayUserCart(cart);
    }

    @Override
    public void createOne() {
        // TODO: user form to add a product
//        cDAO.addToCart(userID, productID, qty, userType);
    }

    @Override
    public void updateOne() {
        // TODO: user form to update a product
//        cDAO.updateCartItemQuantity(userID, productID, newQty, userType);
    }

    @Override
    public void deleteOne() {
        // TODO: delete cart item
//        cDAO.removeFromCart(userID, productID, userType);
    }


    // unused
    @Override
    public void viewAll() {

    }
}
