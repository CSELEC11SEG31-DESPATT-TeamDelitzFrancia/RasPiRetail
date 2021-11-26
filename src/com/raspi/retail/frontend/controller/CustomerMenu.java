package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.model.LoginQuery;
import com.raspi.retail.backend.model.dtos.customer.MemberDTO;
import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.controller.middleware.CartController;
import com.raspi.retail.frontend.controller.middleware.ProductController;
import com.raspi.retail.frontend.view.menu.CustomerPrompt;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class CustomerMenu {

    private static ProductController productControllerMethods = new ProductController();
    private static CartController cartControllerMethods = new CartController();
    private static LoginQuery loginQueryMethods = new LoginQuery();

    public static void index() {
        String username;
        String password;

        CustomerPrompt.printCustomerLoginPrompt();
        username = KBInput.readString("Username: ");
        password = KBInput.readString("Password: ");

        String repeat = "y";
        String repeatSearch = "y";

        if(loginQueryMethods.isLoginValid(username, password) == true){

            //MemberDTO currentMember =

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
                                    //TODO: call method to display all products
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

                    case "a": //add to cart
                        cartControllerMethods.createOne();
                        break;

                    case "vc": //view cart
                        cartControllerMethods.viewAll();
                        //TODO: insert "view cart" functionality
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

    public static boolean login() {
        while (true) {
            //TODO implementation
//            if (isLogin == true) {
//
//            } else {
//            break;
//            }
        }
    }
}
