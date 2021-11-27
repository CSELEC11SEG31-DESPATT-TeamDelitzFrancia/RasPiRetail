package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.model.LoginQuery;
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
    private static LoginQuery loginQueryMethods = new LoginQuery();
    private static UserController userControllerMethods = new UserController();

    public static void index() {
        String username;
        String password;

        CustomerPrompt.printCustomerLoginPrompt();
        username = KBInput.readString("Username: ");
        password = KBInput.readString("Password: ");

        String repeat = "y";
        String repeatSearch = "y";

        if(loginQueryMethods.isLoginValid(username, password) == true){

            int currentMemberId = userControllerMethods.setCurrentMemberID(username);

            String customerFuncChoice;
            CustomerPrompt.printCustomerFunctions();
            customerFuncChoice = KBInput.readString("Your choice: ");

            while (repeat =="y"){
                if(customerFuncChoice.equalsIgnoreCase("vp")){
                    MainPrompt.productSearchOptions();
                    String prodSearchChoice = KBInput.readString("Your choice: ");

                    while(repeatSearch == "y"){
                        if(customerFuncChoice.equalsIgnoreCase("va")){
                            productControllerMethods.viewAll();
                        } else if(customerFuncChoice.equalsIgnoreCase("id")){
                            int searchID = KBInput.readInt("Enter the ID of the product: ");
                            productControllerMethods.viewOneById(searchID);
                        } else if(customerFuncChoice.equalsIgnoreCase("pn")){
                            String searchName = KBInput.readString("Enter the name of the product: ");
                            productControllerMethods.viewOneByName(searchName);
                        } else if(customerFuncChoice.equalsIgnoreCase("pt")){
                            String searchType = KBInput.readString("Enter the type of product: ");
                            productControllerMethods.viewOneByType(searchType);
                        } else if(customerFuncChoice.equalsIgnoreCase("xs")){
                            repeatSearch = "n";
                        } else {
                            System.out.println(">>Invalid Entry<<");
                        }

                    }
                } else if(customerFuncChoice.equalsIgnoreCase("a")){
                    cartControllerMethods.createOne(currentMemberId, CustomerType.CUSTOMER);
                } else if(customerFuncChoice.equalsIgnoreCase("vc")){
                    cartControllerMethods.getUserCart(currentMemberId, CustomerType.CUSTOMER);
                } else if(customerFuncChoice.equalsIgnoreCase("x")){
                    repeat = "n";
                }
            }
        } else {
            System.out.println("ERROR: Incorrect username or password!");
        }
    }
}

