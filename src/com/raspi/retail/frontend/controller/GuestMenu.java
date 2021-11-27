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

        GuestPrompt.printGuestLoginOptions();
        String guestLoginChoice = KBInput.readString("Your choice: ");

        if (guestLoginChoice.equalsIgnoreCase("OT")) {

            String GuestEmail = KBInput.readString("Enter your email to continue as GUEST: ");

            userControllerMethods.signUpAsGuest(GuestEmail);

            int currentGuestId = userControllerMethods.setCurrentGuestID(GuestEmail);

            GuestPrompt.printGuestLoginPrompt();
            GuestPrompt.printGuestFunctions();
            guestFuncChoice = KBInput.readString("Your choice: ");

            while (repeat == "y") {
                switch (guestFuncChoice.toLowerCase()) {
                    case "vp": //view products
                        MainPrompt.productSearchOptions();
                        String prodSearchChoice = KBInput.readString("Your choice: ");
                        String repeatSearch = "y";

                        while (repeatSearch == "y") {
                            switch (prodSearchChoice.toLowerCase()) {
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
                            repeat = "n";
                            break;
                        }
                        break;

                    case "a": //add to cart
                        cartControllerMethods.createOne(currentGuestId, CustomerType.GUEST);
                        break;

                    case "vc": //view cart
                        cartControllerMethods.getUserCart(currentGuestId, CustomerType.GUEST);
                        break;

                    case "x": //end program
                        repeat = "n";
                        break;
                }
            }
        } else if (guestLoginChoice.equalsIgnoreCase("SU")) {
            userControllerMethods.signUp(); //Sign up
        }
    }
}
