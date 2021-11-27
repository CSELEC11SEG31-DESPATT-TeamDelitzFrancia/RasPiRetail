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

            while (repeat == "y"){
                switch (customerFuncChoice.toLowerCase()) {
                    case "vp": //view products
                        MainPrompt.productSearchOptions();
                        String prodSearchChoice = KBInput.readString("Your choice: ");

                        while(repeatSearch == "y"){
                            switch (prodSearchChoice.toLowerCase()){
                                case "va": //View ALL Products
                                    productControllerMethods.viewAll();
                                    //repeatSearch = "n";
                                    break;

                                case "id": //Search for product by ID
                                    int searchID = KBInput.readInt("Enter the ID of the product: ");
                                    productControllerMethods.viewOneById(searchID);
                                    //repeatSearch = "n";
                                    break;

                                case "pn": //Search for product by NAME
                                    String searchName = KBInput.readString("Enter the name of the product: ");
                                    productControllerMethods.viewOneByName(searchName);
                                    //repeatSearch = "n";
                                    break;

                                case "pt": //Search for product by TYPE
                                    String searchType = KBInput.readString("Enter the type of product: ");
                                    productControllerMethods.viewOneByType(searchType);
                                    //repeatSearch = "n";
                                    break;

                                case "xs": //Stop searching
                                    repeatSearch = "n";
                                    break;
                            }
                            repeat = "n";
                            break;
                        }

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
        } else {
            System.out.println("ERROR: Incorrect username or password!");
        }
    }
}

