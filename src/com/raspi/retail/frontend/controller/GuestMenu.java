package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.controller.middleware.ProductController;
import com.raspi.retail.frontend.view.menu.GuestPrompt;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class GuestMenu {

    private static ProductController productControllerMethods = new ProductController();

    public static void index() {
        String guestFuncChoice;
        String repeat = "y";

        GuestPrompt.printGuestLoginPrompt();
        GuestPrompt.printGuestFunctions();
        guestFuncChoice = KBInput.readString("Your choice: ");

        while (repeat == "y"){
            switch (guestFuncChoice.toLowerCase()) {
                case "vp": //view products
                    MainPrompt.productSearchOptions();
                    String prodSearchChoice = KBInput.readString("Your choice: ");
                    String repeatSearch = "y";

                    while(repeatSearch == "y"){
                        switch (prodSearchChoice.toLowerCase()){
                            case "va": //View ALL Products
                                //ProductDAO.getAllProducts();
                                //TODO: call method to display all products
                                productControllerMethods.viewAll();
                                break;

                            case "id": //Search for product by ID
                                int searchID = KBInput.readInt("Enter the ID of the product: ");
                                productControllerMethods.viewOneById(searchID);
                                //TODO: call method to display a product by ID
                                break;

                            case "pn": //Search for product by NAME
                                String searchName = KBInput.readString("Enter the name of the product: ");
                                productControllerMethods.viewOneByName(searchName);
                                //TODO: call method to display a product by NAME
                                break;

                            case "pt": //Search for product by TYPE
                                String searchType = KBInput.readString("Enter the type of product: ");
                                productControllerMethods.viewOneByType(searchType);
                                //TODO: call method to display a product by TYPE
                                break;

                            case "xs": //Stop searching
                                repeatSearch = "n";
                                break;
                        }
                        break;
                    }

                case "su": //sign up
                    //TODO: add "sign-up" functionality
                    break;

                case "a": //add to cart
                    //TODO: insert "add to cart" functionality
                    break;

                case "vc": //view cart
                    //TODO: insert "view cart" functionality
                    break;

                case "x": //end program
                    repeat = "n";
                    break;
            }
        }
    }

    public static void guestLogin() {

    }
}
