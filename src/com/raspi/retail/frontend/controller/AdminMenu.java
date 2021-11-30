package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.controller.middleware.PackageController;
import com.raspi.retail.frontend.controller.middleware.ProductController;
import com.raspi.retail.frontend.controller.middleware.UserController;
import com.raspi.retail.frontend.view.menu.AdminPrompt;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class AdminMenu {

    private static ProductController productControllerMethods = new ProductController();
    private static UserController userControllerMethods = new UserController();
    private static PackageController packageControllerMethods = new PackageController();

    public static void index() {
        String username;
        String password;

        String repeat = "y";
        String repeatSearch = "y";

        AdminPrompt.printAdminLoginPrompt();
        username = KBInput.readString("Username: ");
        password = KBInput.readString("Password: ");

        if(userControllerMethods.isLoginValid(CustomerType.ADMIN, username, password)){

            while (repeat.equalsIgnoreCase("y")){
                String adminFuncChoice;
                AdminPrompt.printAdminFunctions();
                adminFuncChoice = KBInput.readString("Your choice: ");

                switch (adminFuncChoice.toLowerCase()) {
                    case "u": //update product
                        int itemUpdateId = KBInput.readInt("Enter the ID of the item you'd like to UPDATE: ");
                        productControllerMethods.updateOne(itemUpdateId);
                        repeat = "n";
                        break;

                    case "a": //add product
                        productControllerMethods.createOne();
                        repeat = "n";
                        break;

                    case "d": //delete product
                        int itemDeleteId = KBInput.readInt("Enter the ID of the item you'd like to DELETE: ");
                        productControllerMethods.deleteOne(itemDeleteId);
                        repeat = "n";
                        break;

                    case "vp": //view products
                        MainPrompt.productSearchOptions();
                        String prodSearchChoice = KBInput.readString("Your choice: ");

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
                            break;
                        }
                    case "pm": //package manager
                        AdminPrompt.printAdminPackageFunctions();
                        String AdminPkgFuncChoice = KBInput.readString("\nYour choice: ");

                        if (AdminPkgFuncChoice.equalsIgnoreCase("vap")) { //view all packages
                            CustomerType searchUserType = CustomerType.valueOf(KBInput.readString("Enter the customer type of the package list (Customer/Guest)").toUpperCase());

                            packageControllerMethods.adminViewPackages(searchUserType);

//                        } else if (AdminPkgFuncChoice.equalsIgnoreCase("uid")) {
//                            CustomerType searchUserType = CustomerType.valueOf(KBInput.readString("Enter the customer type of the package list (Customer/Guest)").toUpperCase());
//                            int searchPkgID = KBInput.readInt("Enter the package ID to search: ");
//
//                            packageControllerMethods.adminViewPackageByID(searchUserType, searchPkgID);

                        } else if (AdminPkgFuncChoice.equalsIgnoreCase("rp")) {
                            CustomerType searchUserType = CustomerType.valueOf(KBInput.readString("Enter the customer type of the package list (Customer/Guest)").toUpperCase());
                            int deletePkgID = KBInput.readInt("Enter the package ID to delete: ");

                            packageControllerMethods.adminDeletePackageByID(searchUserType, deletePkgID);
                        } else
                            System.out.println("Invalid Entry!");

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
