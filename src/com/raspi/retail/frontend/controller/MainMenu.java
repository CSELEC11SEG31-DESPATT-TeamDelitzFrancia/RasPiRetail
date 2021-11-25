package com.raspi.retail.frontend.controller;

import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.view.menu.MainPrompt;

public class MainMenu {

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
                    com.raspi.retail.frontend.controller.CustomerMenu.index();
                    break;

                case "g":
                    com.raspi.retail.frontend.controller.GuestMenu.index();
                    break;

                case "e":
                    break;

                default:
                    System.out.println("Invalid User Choice!");
            }

        }

    }


}
