package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.model.dtos.enums.PackageType;
import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.controller.middleware.CartController;
import com.raspi.retail.frontend.controller.middleware.PackageController;
import com.raspi.retail.frontend.controller.middleware.UserController;
import com.raspi.retail.frontend.controller.middleware.ProductController;
import com.raspi.retail.frontend.view.menu.GuestPrompt;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class GuestMenu {

    private static ProductController productControllerMethods = new ProductController();
    private static UserController userControllerMethods = new UserController();
    private static CartController cartControllerMethods = new CartController();
    private static PackageController packageControllerMethods = new PackageController();

    public static void index() {
        String guestFuncChoice;

        GuestPrompt.printGuestLoginOptions();
        String guestLoginChoice = KBInput.readString("Your choice: ");

        if (guestLoginChoice.equalsIgnoreCase("OT")) {

            String GuestEmail = KBInput.readString("Enter your email to continue as GUEST: ");

            userControllerMethods.signUpAsGuest(GuestEmail);

            int currentGuestId = userControllerMethods.setCurrentGuestID(GuestEmail);


            while (true) {
                GuestPrompt.printGuestLoginPrompt();
                GuestPrompt.printGuestFunctions();
                guestFuncChoice = KBInput.readString("Your choice: ");


                if(guestFuncChoice.equalsIgnoreCase("vp")){

                    while (true) {
                        MainPrompt.productSearchOptions();
                        String prodSearchChoice = KBInput.readString("Your choice: ");
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
                        } else if(prodSearchChoice.equalsIgnoreCase("c")){
                            break;
                        } else if(!prodSearchChoice.equalsIgnoreCase("va") || !prodSearchChoice.equalsIgnoreCase("id") || !prodSearchChoice.equalsIgnoreCase("pn") || !prodSearchChoice.equalsIgnoreCase("pt") || !prodSearchChoice.equalsIgnoreCase("xs")){
                            System.out.println(">>Invalid Entry<<");
                        }
                    }

                } else if(guestFuncChoice.equalsIgnoreCase("a")){
                    cartControllerMethods.createOne(currentGuestId, CustomerType.GUEST);
                } else if(guestFuncChoice.equalsIgnoreCase("vc")){
                    cartControllerMethods.getUserCart(currentGuestId, CustomerType.GUEST);
                } else if(guestFuncChoice.equalsIgnoreCase("co")){
                    System.out.println("Select an option for packaging: ");
                    System.out.println("\n[1]: Standard Electrostatic Packaging");
                    System.out.println("[2]: Bubble Wrap Electrostatic Packaging\n");
                    String pkgTypeChoice = KBInput.readString("Enter Here: ");
                    PackageType pkgType = pkgTypeChoice.equals("1")
                            ? PackageType.STANDARD_ELECTROSTATIC
                            : pkgTypeChoice.equals("2")
                                ? PackageType.BUBBLEWRAP_ELECTROSTATIC
                                : null;
                    if(pkgType != null)
                        packageControllerMethods.checkoutCustomer(CustomerType.GUEST, pkgType, currentGuestId);
                    else {
                        System.out.println("Invalid Choice! Please select from the options for packaging.");
                    }
                } else if(guestFuncChoice.equalsIgnoreCase("x")){
                    break;
                } else {
                    System.out.println(">>Invalid Entry<<");
                }
            }
        } else if (guestLoginChoice.equalsIgnoreCase("SU")) {
            userControllerMethods.signUp(); //Sign up
        }
    }
}
