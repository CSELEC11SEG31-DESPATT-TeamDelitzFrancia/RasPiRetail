package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.controller.middleware.CartController;
import com.raspi.retail.frontend.controller.middleware.ProductController;
import com.raspi.retail.frontend.controller.middleware.UserController;
import com.raspi.retail.frontend.view.menu.CustomerPrompt;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class CustomerMenu {

    private static ProductController productControllerMethods = new ProductController();
    private static CartController cartControllerMethods = new CartController();
    private static UserController userControllerMethods = new UserController();

    public static void index() {
        String username;
        String password;

        CustomerPrompt.printCustomerLoginPrompt();
        username = KBInput.readString("Username: ");
        password = KBInput.readString("Password: ");

        if(userControllerMethods.isLoginValid(CustomerType.CUSTOMER, username, password)){

            int currentMemberId = userControllerMethods.setCurrentMemberID(username);

            while (true){
                String customerFuncChoice;
                CustomerPrompt.printCustomerFunctions();
                customerFuncChoice = KBInput.readString("Your choice: ");

                if(customerFuncChoice.equalsIgnoreCase("vp")){

                    while(true){
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
                        } else {
                            System.out.println(">>Invalid Entry<<");
                        }

                    }
                } else if(customerFuncChoice.equalsIgnoreCase("a")){
                    cartControllerMethods.createOne(currentMemberId, CustomerType.CUSTOMER);
                } else if(customerFuncChoice.equalsIgnoreCase("vc")){
                    cartControllerMethods.getUserCart(currentMemberId, CustomerType.CUSTOMER);
                } else if(customerFuncChoice.equalsIgnoreCase("x")){
                    break;
                }
            }
        } else {
            System.out.println("ERROR: Incorrect username or password!");
        }
    }
}

