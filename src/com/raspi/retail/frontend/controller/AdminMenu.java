package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.model.daos.ProductDAO;
import com.raspi.retail.backend.model.factory.DAOFactory;
import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.controller.middleware.ProductController;
import com.raspi.retail.frontend.view.display.ProductDisplay;
import com.raspi.retail.frontend.view.menu.AdminPrompt;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class AdminMenu {

    private static ProductController productControllerMethods = new ProductController();

    public static void index() {
        String username;
        String password;

        String repeat = "y";
        String repeatSearch = "y";

        AdminPrompt.printAdminLoginPrompt();
        username = KBInput.readString("Username: ");
        password = KBInput.readString("Password: ");

        if(username.equals("admin") && password.equals("password1")){
            String adminFuncChoice;
            AdminPrompt.printAdminFunctions();
            adminFuncChoice = KBInput.readString("Your choice: ");

            while (repeat == "y"){
                switch (adminFuncChoice.toLowerCase()) {
                    case "u": //update product
                        int itemUpdateId = KBInput.readInt("Enter the ID of the item you'd like to UPDATE: ");
                        productControllerMethods.updateOne(itemUpdateId);
                        break;

                    case "a": //add product
                        productControllerMethods.createOne();
                        break;

                    case "d": //delete product
                        int itemDeleteId = KBInput.readInt("Enter the ID of the item you'd like to DELETE: ");
                        productControllerMethods.deleteOne(itemDeleteId);
                        break;

                    case "vp": //view products
                        MainPrompt.productSearchOptions();
                        String prodSearchChoice = KBInput.readString("Your choice: ");

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
                    case "x": //terminates program
                        repeat = "n";
                        break;
                }
            }
        } else {
            System.out.println("ERROR: Incorrect username or password!");
        }
    }

}
