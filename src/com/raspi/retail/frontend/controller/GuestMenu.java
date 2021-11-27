package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.controller.middleware.CartController;
import com.raspi.retail.frontend.controller.middleware.UserController;
import com.raspi.retail.frontend.controller.middleware.ProductController;
import com.raspi.retail.frontend.view.menu.GuestPrompt;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class GuestMenu {

    private static ProductController productControllerMethods = new ProductController();
    private static UserController userControllerMethods = new UserController();
    private static CartController cartControllerMethods = new CartController();

    public static void index() {
        String guestFuncChoice;
        String repeat = "y";

        String username = KBInput.readString("Enter your temporary username: ");

        int currentMemberId = userControllerMethods.setCurrentMemberID(username);

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
                                productControllerMethods.viewAll();
                                break;

                            case "id": //Search for product by ID
                                int searchID = KBInput.readInt("Enter the ID of the product: ");
                                productControllerMethods.viewOneById(searchID);
                                break;

                            case "pn": //Search for product by NAME
                                String searchName = KBInput.readString("Enter the name of the product: ");
                                productControllerMethods.viewOneByName(searchName);
                                break;

                            case "pt": //Search for product by TYPE
                                String searchType = KBInput.readString("Enter the type of product: ");
                                productControllerMethods.viewOneByType(searchType);
                                break;

                            case "xs": //Stop searching
                                repeatSearch = "n";
                                break;
                        }
                        break;
                    }

                case "su": //sign up
                    userControllerMethods.signUp(); //TODO: Add repeat = "n" to necessary methods
                    repeat = "n";
                    break;

                case "a": //add to cart
                    cartControllerMethods.createOne(currentMemberId, CustomerType.CUSTOMER);
                    break;

                case "vc": //view cart
                    cartControllerMethods.getUserCart(currentMemberId, CustomerType.CUSTOMER);
                    break;

                case "x": //end program
                    repeat = "n";
                    break;
            }
        }
    }
}
