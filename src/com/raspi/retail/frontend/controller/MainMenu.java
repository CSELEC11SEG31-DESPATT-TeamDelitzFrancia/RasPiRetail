package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.controller.middleware.UserController;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class MainMenu {

    private static UserController userControllerMethods = new UserController();

    public static void index() {
        String userChoice;

        while(true) {
            MainPrompt.welcome();
            MainPrompt.loginOptions();

            userChoice = KBInput.readString("Your choice: ");

            switch (userChoice.toLowerCase()) {
                case "a":
                    AdminMenu.index();
                    break;

                case "c":
                    CustomerMenu.index();
                    break;

                case "g":
                    GuestMenu.index();
                    break;

                case "su": //sign up
                    userControllerMethods.signUp();
                    break;

                case "e":
                    return;

                default:
                    System.out.println("Invalid User Choice!");
            }

        }

    }


}
