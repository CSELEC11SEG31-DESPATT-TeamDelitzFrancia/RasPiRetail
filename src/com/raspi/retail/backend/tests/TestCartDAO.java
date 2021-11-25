package com.raspi.retail.backend.tests;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.CartDAO;
import com.raspi.retail.backend.model.dtos.CartItem;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.DBDriverService;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCartDAO {

    ModelFactory daoF = ModelFactoryProducer.createFactory("dao");
    CartDAO cDAO = (CartDAO) daoF.createDAOPrototype("cart");

    @Test
    public void initTablesIfNotExists() {
        DBDriverService.buildSqlDatabase();
    }

    /*
         _           _
        | |_ ___ ___| |_ ___
        | __/ _ / __| __/ __|
        | ||  __\__ | |_\__ \
        \__\___|___/\__|___/
     */

    @Test
    public void testGetUserCart() {
        ArrayList<CartItem> cart = cDAO.getUserCart(1, CustomerType.CUSTOMER);

        cart.forEach((cartItem -> {
            System.out.println();
            System.out.println(cartItem.getProductName());
            System.out.println(cartItem.getQuantity());
            System.out.println(cartItem.getSubtotal());
            System.out.println();
        }));

        assertTrue("Check if customer returns customer cart.", cart.size() > 0);

    }

    @Test
    public void testAddToCart() {

        // preserve testAddToCartConsole (current console)
        PrintStream testAddToCartStream = System.out;
        // set new console for addToCart and set as default console (addToCart console)
        ByteArrayOutputStream addToCartStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(addToCartStream));
            // scrape sysout from this command to addToCartStream console
            cDAO.addToCart(1, 6,2, CustomerType.CUSTOMER);

        // restore testAddToCartConsole
        System.setOut(testAddToCartStream);

        // == FROM THIS POINT ONWARD, SCRAPED OUTPUT IS NOW IN addToCartStream == //
        assertEquals("Check if item returns successfully added.", "Item Successfully added to cart!\n", addToCartStream.toString());


    }

    @Test
    public void testUpdateCartItemQuantity() {

        PrintStream testUpdateCartItemStream = System.out;

        ByteArrayOutputStream updateCartItemStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(updateCartItemStream));
            cDAO.updateCartItemQuantity(1, 6, 1, CustomerType.CUSTOMER);

        System.setOut(testUpdateCartItemStream);

        assertEquals("Check if item returns successfully updated." , "Cart Item successfully updated!\n", updateCartItemStream.toString());

    }

    @Test
    public void testDeleteCartItem() {

        PrintStream testDeleteCartItemStream = System.out;

        ByteArrayOutputStream deleteCartItemStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(deleteCartItemStream));
            cDAO.removeFromCart(1, 6, CustomerType.CUSTOMER);

        System.setOut(testDeleteCartItemStream);

        assertEquals("Check if item returns successfully deleted.", "Cart Item Successfully Deleted!\n", deleteCartItemStream.toString());
    }

}
