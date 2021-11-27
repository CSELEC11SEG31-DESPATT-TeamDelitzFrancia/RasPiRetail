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

                String repeatInnerMenu = "y";

//                while(repeatInnerMenu == "y"){
                    if(guestFuncChoice.equalsIgnoreCase("vp")){

                        MainPrompt.productSearchOptions();
                        String prodSearchChoice = KBInput.readString("Your choice: ");
                        String repeatSearch = "y";

                        while (repeatSearch == "y") {
                            if(prodSearchChoice.equalsIgnoreCase("va")){
                                productControllerMethods.viewAll();
                            } else if(prodSearchChoice.equalsIgnoreCase("id")){
                                int searchID = KBInput.readInt("Enter the ID of the product: ");
                                productControllerMethods.viewOneById(searchID);
                            } else if(prodSearchChoice.equalsIgnoreCase("pn")){
                                String searchName = KBInput.readString("Enter the name of the product: ");
                                productControllerMethods.viewOneByName(searchName);
                            } else if(prodSearchChoice.equalsIgnoreCase("pt")){
                                String searchType = KBInput.readString("Enter the type of product: ");
                                productControllerMethods.viewOneByType(searchType);
                            } else if(prodSearchChoice.equalsIgnoreCase("xs")){
                                repeatSearch = "n";
                            } else if(!prodSearchChoice.equalsIgnoreCase("va") || !prodSearchChoice.equalsIgnoreCase("id") || !prodSearchChoice.equalsIgnoreCase("pn") || !prodSearchChoice.equalsIgnoreCase("pt") || !prodSearchChoice.equalsIgnoreCase("xs")){
                                System.out.println(">>Invalid Entry<<");
                            }
                        }
                    } else if(guestFuncChoice.equalsIgnoreCase("a")){
                        cartControllerMethods.createOne(currentGuestId, CustomerType.GUEST); //TODO: Infinite loop bug
                    } else if(guestFuncChoice.equalsIgnoreCase("vc")){
                        cartControllerMethods.getUserCart(currentGuestId, CustomerType.GUEST); //TODO: Infinite loop bug
                    } else if(guestFuncChoice.equalsIgnoreCase("x")){
                        repeat = "n";
                    } else if(!guestFuncChoice.equalsIgnoreCase("vp") || !guestFuncChoice.equalsIgnoreCase("a") || !guestFuncChoice.equalsIgnoreCase("vc") || !guestFuncChoice.equalsIgnoreCase("x")){
                        System.out.println(">>Invalid Entry<<");
                        repeat = "n";
                    }
//                }
            }
        } else if (guestLoginChoice.equalsIgnoreCase("SU")) {
            userControllerMethods.signUp(); //Sign up
        }
    }
}
