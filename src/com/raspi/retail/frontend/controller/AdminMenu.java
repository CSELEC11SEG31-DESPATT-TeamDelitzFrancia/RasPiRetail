package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.view.menu.AdminPrompt;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class AdminMenu {

    public static void index() {
        String username;
        String password;

        String repeat = "y";
        String repeatSearch = "y";

        AdminPrompt.printAdminLoginPrompt();
        username = KBInput.readString("Username: ");
        password = KBInput.readString("Password: ");

        if(username.equals("admin") && password.equals("password1")){ //TODO: link the username and password variables to SQL query in util.SqlQueries
            String adminFuncChoice;
            AdminPrompt.printAdminFunctions();
            adminFuncChoice = KBInput.readString("Your choice: ");

            while (repeat == "y"){
                switch (adminFuncChoice.toLowerCase()) {
                    case "u":
                        int itemUpdateId = KBInput.readInt("Enter the ID of the item you'd like to UPDATE: ");

                        //ProductDAO.updateProduct(itemUpdateId, );
                        //TODO: call method to search for the ID of a product to UPDATE
                        break;

                    case "a":
                        //ProductDAO.addProduct();
                        //TODO: call method to ADD product to DB
                        break;

                    case "d":
                        int itemDeleteId = KBInput.readInt("Enter the ID of the item you'd like to DELETE: ");
                        //ProductDAO.deleteProduct(itemDeleteId);
                        //TODO: call method to search for the ID of a product to DELETE
                        break;

                    case "vp": //view products
                        MainPrompt.productSearchOptions();
                        String prodSearchChoice = KBInput.readString("Your choice: ");

                        while(repeatSearch == "y"){
                            switch (prodSearchChoice.toLowerCase()){
                                case "va": //View ALL Products
                                    //ProductDAO.getAllProducts();
                                    //TODO: call method to display all products
                                    break;

                                case "id": //Search for product by ID
                                    String searchID = KBInput.readString("Enter the ID of the product: ");
                                    //ProductDAO.getProductById(searchID);
                                    //TODO: call method to display a product by ID
                                    break;

                                case "pn": //Search for product by NAME
                                    String searchName = KBInput.readString("Enter the name of the product: ");
                                    //ProductDAO.getProductByName(searchName);
                                    //TODO: call method to display a product by NAME
                                    break;

                                case "pt": //Search for product by TYPE
                                    String searchType = KBInput.readString("Enter the type of product: ");
                                    //ProductDAO.getProductByType(searchType);
                                    //TODO: call method to display a product by TYPE
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

    public static boolean login() {
        while(true) {
        //TODO implementation
        //if (LoginUtils.LoginChecker(username, password) == true) {
            index();
        //} else {
           // break;
        //}
        }
    }

}
